import axios from 'axios';
import {
  ICrudDeleteAction,
  ICrudGetAction,
  ICrudGetAllAction,
  ICrudPutAction,
  ICrudSearchAction,
  loadMoreDataWhenScrolled,
  parseHeaderForLinks,
} from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { FAILURE, REQUEST, SUCCESS } from 'app/shared/reducers/action-type.util';

import { defaultValue, IPayment } from 'app/shared/model/payment.model';

export const ACTION_TYPES = {
  SEARCH_PAYMENTS: 'payment/SEARCH_PAYMENTS',
  FETCH_PAYMENT_LIST: 'payment/FETCH_PAYMENT_LIST',
  FETCH_PAYMENT: 'payment/FETCH_PAYMENT',
  CREATE_PAYMENT: 'payment/CREATE_PAYMENT',
  UPDATE_PAYMENT: 'payment/UPDATE_PAYMENT',
  DELETE_PAYMENT: 'payment/DELETE_PAYMENT',
  SET_BLOB: 'payment/SET_BLOB',
  RESET: 'payment/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPayment>,
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type PaymentState = Readonly<typeof initialState>;

// Reducer

export default (state: PaymentState = initialState, action): PaymentState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_PAYMENTS):
    case REQUEST(ACTION_TYPES.FETCH_PAYMENT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PAYMENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_PAYMENT):
    case REQUEST(ACTION_TYPES.UPDATE_PAYMENT):
    case REQUEST(ACTION_TYPES.DELETE_PAYMENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.SEARCH_PAYMENTS):
    case FAILURE(ACTION_TYPES.FETCH_PAYMENT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PAYMENT):
    case FAILURE(ACTION_TYPES.CREATE_PAYMENT):
    case FAILURE(ACTION_TYPES.UPDATE_PAYMENT):
    case FAILURE(ACTION_TYPES.DELETE_PAYMENT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.SEARCH_PAYMENTS):
    case SUCCESS(ACTION_TYPES.FETCH_PAYMENT_LIST): {
      const links = parseHeaderForLinks(action.payload.headers.link);

      return {
        ...state,
        loading: false,
        links,
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links),
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    }
    case SUCCESS(ACTION_TYPES.FETCH_PAYMENT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_PAYMENT):
    case SUCCESS(ACTION_TYPES.UPDATE_PAYMENT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_PAYMENT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.SET_BLOB: {
      const { name, data, contentType } = action.payload;
      return {
        ...state,
        entity: {
          ...state.entity,
          [name]: data,
          [name + 'ContentType']: contentType,
        },
      };
    }
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/payments';
const apiSearchUrl = 'api/_search/payments';

// Actions

export const getSearchEntities: ICrudSearchAction<IPayment> = (query, page, size, sort) => ({
  type: ACTION_TYPES.SEARCH_PAYMENTS,
  payload: axios.get<IPayment>(`${apiSearchUrl}?query=${query}${sort ? `&page=${page}&size=${size}&sort=${sort}` : ''}`),
});

export const getEntities: ICrudGetAllAction<IPayment> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_PAYMENT_LIST,
    payload: axios.get<IPayment>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IPayment> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PAYMENT,
    payload: axios.get<IPayment>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IPayment> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PAYMENT,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const updateEntity: ICrudPutAction<IPayment> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PAYMENT,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPayment> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PAYMENT,
    payload: axios.delete(requestUrl),
  });
  return result;
};

export const setBlob = (name, data, contentType?) => ({
  type: ACTION_TYPES.SET_BLOB,
  payload: {
    name,
    data,
    contentType,
  },
});

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
