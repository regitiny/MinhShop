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

import {cleanEntity} from 'app/shared/util/entity-utils';
import {FAILURE, REQUEST, SUCCESS} from 'app/shared/reducers/action-type.util';

import {defaultValue, IHanhChinhVN} from 'app/shared/model/hanh-chinh-vn.model';

export const ACTION_TYPES = {
  SEARCH_HANHCHINHVNS: 'hanhChinhVN/SEARCH_HANHCHINHVNS',
  FETCH_HANHCHINHVN_LIST: 'hanhChinhVN/FETCH_HANHCHINHVN_LIST',
  FETCH_HANHCHINHVN: 'hanhChinhVN/FETCH_HANHCHINHVN',
  CREATE_HANHCHINHVN: 'hanhChinhVN/CREATE_HANHCHINHVN',
  UPDATE_HANHCHINHVN: 'hanhChinhVN/UPDATE_HANHCHINHVN',
  DELETE_HANHCHINHVN: 'hanhChinhVN/DELETE_HANHCHINHVN',
  RESET: 'hanhChinhVN/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IHanhChinhVN>,
  entity: defaultValue,
  links: {next: 0},
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type HanhChinhVNState = Readonly<typeof initialState>;

// Reducer

export default (state: HanhChinhVNState = initialState, action): HanhChinhVNState =>
{
  switch (action.type)
  {
    case REQUEST(ACTION_TYPES.SEARCH_HANHCHINHVNS):
    case REQUEST(ACTION_TYPES.FETCH_HANHCHINHVN_LIST):
    case REQUEST(ACTION_TYPES.FETCH_HANHCHINHVN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_HANHCHINHVN):
    case REQUEST(ACTION_TYPES.UPDATE_HANHCHINHVN):
    case REQUEST(ACTION_TYPES.DELETE_HANHCHINHVN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.SEARCH_HANHCHINHVNS):
    case FAILURE(ACTION_TYPES.FETCH_HANHCHINHVN_LIST):
    case FAILURE(ACTION_TYPES.FETCH_HANHCHINHVN):
    case FAILURE(ACTION_TYPES.CREATE_HANHCHINHVN):
    case FAILURE(ACTION_TYPES.UPDATE_HANHCHINHVN):
    case FAILURE(ACTION_TYPES.DELETE_HANHCHINHVN):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.SEARCH_HANHCHINHVNS):
    case SUCCESS(ACTION_TYPES.FETCH_HANHCHINHVN_LIST):
    {
      const links = parseHeaderForLinks(action.payload.headers.link);

      return {
        ...state,
        loading: false,
        links,
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links),
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    }
    case SUCCESS(ACTION_TYPES.FETCH_HANHCHINHVN):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_HANHCHINHVN):
    case SUCCESS(ACTION_TYPES.UPDATE_HANHCHINHVN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_HANHCHINHVN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/hanh-chinh-vns';
const apiSearchUrl = 'api/_search/hanh-chinh-vns';

// Actions

export const getSearchEntities: ICrudSearchAction<IHanhChinhVN> = (query, page, size, sort) => ({
  type: ACTION_TYPES.SEARCH_HANHCHINHVNS,
  payload: axios.get<IHanhChinhVN>(`${apiSearchUrl}?query=${query}${sort ? `&page=${page}&size=${size}&sort=${sort}` : ''}`),
});

export const getEntities: ICrudGetAllAction<IHanhChinhVN> = (page, size, sort) =>
{
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_HANHCHINHVN_LIST,
    payload: axios.get<IHanhChinhVN>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IHanhChinhVN> = id =>
{
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_HANHCHINHVN,
    payload: axios.get<IHanhChinhVN>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IHanhChinhVN> = entity => async dispatch =>
{
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_HANHCHINHVN,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const updateEntity: ICrudPutAction<IHanhChinhVN> = entity => async dispatch =>
{
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_HANHCHINHVN,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IHanhChinhVN> = id => async dispatch =>
{
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_HANHCHINHVN,
    payload: axios.delete(requestUrl),
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
