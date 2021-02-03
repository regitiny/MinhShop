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

import { ISimplePost, defaultValue } from 'app/shared/model/simple-post.model';

export const ACTION_TYPES = {
  SEARCH_SIMPLEPOSTS: 'simplePost/SEARCH_SIMPLEPOSTS',
  FETCH_SIMPLEPOST_LIST: 'simplePost/FETCH_SIMPLEPOST_LIST',
  FETCH_SIMPLEPOST: 'simplePost/FETCH_SIMPLEPOST',
  CREATE_SIMPLEPOST: 'simplePost/CREATE_SIMPLEPOST',
  UPDATE_SIMPLEPOST: 'simplePost/UPDATE_SIMPLEPOST',
  DELETE_SIMPLEPOST: 'simplePost/DELETE_SIMPLEPOST',
  SET_BLOB: 'simplePost/SET_BLOB',
  RESET: 'simplePost/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISimplePost>,
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type SimplePostState = Readonly<typeof initialState>;

// Reducer

export default (state: SimplePostState = initialState, action): SimplePostState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_SIMPLEPOSTS):
    case REQUEST(ACTION_TYPES.FETCH_SIMPLEPOST_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SIMPLEPOST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_SIMPLEPOST):
    case REQUEST(ACTION_TYPES.UPDATE_SIMPLEPOST):
    case REQUEST(ACTION_TYPES.DELETE_SIMPLEPOST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.SEARCH_SIMPLEPOSTS):
    case FAILURE(ACTION_TYPES.FETCH_SIMPLEPOST_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SIMPLEPOST):
    case FAILURE(ACTION_TYPES.CREATE_SIMPLEPOST):
    case FAILURE(ACTION_TYPES.UPDATE_SIMPLEPOST):
    case FAILURE(ACTION_TYPES.DELETE_SIMPLEPOST):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.SEARCH_SIMPLEPOSTS):
    case SUCCESS(ACTION_TYPES.FETCH_SIMPLEPOST_LIST): {
      const links = parseHeaderForLinks(action.payload.headers.link);

      return {
        ...state,
        loading: false,
        links,
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links),
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    }
    case SUCCESS(ACTION_TYPES.FETCH_SIMPLEPOST):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_SIMPLEPOST):
    case SUCCESS(ACTION_TYPES.UPDATE_SIMPLEPOST):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_SIMPLEPOST):
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

const apiUrl = 'api/simple-posts';
const apiSearchUrl = 'api/_search/simple-posts';

// Actions

export const getSearchEntities: ICrudSearchAction<ISimplePost> = (query, page, size, sort) => ({
  type: ACTION_TYPES.SEARCH_SIMPLEPOSTS,
  payload: axios.get<ISimplePost>(`${apiSearchUrl}?query=${query}${sort ? `&page=${page}&size=${size}&sort=${sort}` : ''}`),
});

export const getEntities: ICrudGetAllAction<ISimplePost> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_SIMPLEPOST_LIST,
    payload: axios.get<ISimplePost>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<ISimplePost> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SIMPLEPOST,
    payload: axios.get<ISimplePost>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ISimplePost> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SIMPLEPOST,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const updateEntity: ICrudPutAction<ISimplePost> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SIMPLEPOST,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISimplePost> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SIMPLEPOST,
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
