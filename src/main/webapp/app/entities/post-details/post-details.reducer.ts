import axios from 'axios';
import {
  ICrudSearchAction,
  parseHeaderForLinks,
  loadMoreDataWhenScrolled,
  ICrudGetAction,
  ICrudGetAllAction,
  ICrudPutAction,
  ICrudDeleteAction,
} from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPostDetails, defaultValue } from 'app/shared/model/post-details.model';

export const ACTION_TYPES = {
  SEARCH_POSTDETAILS: 'postDetails/SEARCH_POSTDETAILS',
  FETCH_POSTDETAILS_LIST: 'postDetails/FETCH_POSTDETAILS_LIST',
  FETCH_POSTDETAILS: 'postDetails/FETCH_POSTDETAILS',
  CREATE_POSTDETAILS: 'postDetails/CREATE_POSTDETAILS',
  UPDATE_POSTDETAILS: 'postDetails/UPDATE_POSTDETAILS',
  DELETE_POSTDETAILS: 'postDetails/DELETE_POSTDETAILS',
  SET_BLOB: 'postDetails/SET_BLOB',
  RESET: 'postDetails/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPostDetails>,
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type PostDetailsState = Readonly<typeof initialState>;

// Reducer

export default (state: PostDetailsState = initialState, action): PostDetailsState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_POSTDETAILS):
    case REQUEST(ACTION_TYPES.FETCH_POSTDETAILS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_POSTDETAILS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_POSTDETAILS):
    case REQUEST(ACTION_TYPES.UPDATE_POSTDETAILS):
    case REQUEST(ACTION_TYPES.DELETE_POSTDETAILS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.SEARCH_POSTDETAILS):
    case FAILURE(ACTION_TYPES.FETCH_POSTDETAILS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_POSTDETAILS):
    case FAILURE(ACTION_TYPES.CREATE_POSTDETAILS):
    case FAILURE(ACTION_TYPES.UPDATE_POSTDETAILS):
    case FAILURE(ACTION_TYPES.DELETE_POSTDETAILS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.SEARCH_POSTDETAILS):
    case SUCCESS(ACTION_TYPES.FETCH_POSTDETAILS_LIST): {
      const links = parseHeaderForLinks(action.payload.headers.link);

      return {
        ...state,
        loading: false,
        links,
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links),
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    }
    case SUCCESS(ACTION_TYPES.FETCH_POSTDETAILS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_POSTDETAILS):
    case SUCCESS(ACTION_TYPES.UPDATE_POSTDETAILS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_POSTDETAILS):
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

const apiUrl = 'api/post-details';
const apiSearchUrl = 'api/_search/post-details';

// Actions

export const getSearchEntities: ICrudSearchAction<IPostDetails> = (query, page, size, sort) => ({
  type: ACTION_TYPES.SEARCH_POSTDETAILS,
  payload: axios.get<IPostDetails>(`${apiSearchUrl}?query=${query}${sort ? `&page=${page}&size=${size}&sort=${sort}` : ''}`),
});

export const getEntities: ICrudGetAllAction<IPostDetails> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_POSTDETAILS_LIST,
    payload: axios.get<IPostDetails>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IPostDetails> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_POSTDETAILS,
    payload: axios.get<IPostDetails>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IPostDetails> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_POSTDETAILS,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const updateEntity: ICrudPutAction<IPostDetails> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_POSTDETAILS,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPostDetails> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_POSTDETAILS,
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
