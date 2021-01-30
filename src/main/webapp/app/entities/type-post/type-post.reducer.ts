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

import { ITypePost, defaultValue } from 'app/shared/model/type-post.model';

export const ACTION_TYPES = {
  SEARCH_TYPEPOSTS: 'typePost/SEARCH_TYPEPOSTS',
  FETCH_TYPEPOST_LIST: 'typePost/FETCH_TYPEPOST_LIST',
  FETCH_TYPEPOST: 'typePost/FETCH_TYPEPOST',
  CREATE_TYPEPOST: 'typePost/CREATE_TYPEPOST',
  UPDATE_TYPEPOST: 'typePost/UPDATE_TYPEPOST',
  DELETE_TYPEPOST: 'typePost/DELETE_TYPEPOST',
  RESET: 'typePost/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITypePost>,
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type TypePostState = Readonly<typeof initialState>;

// Reducer

export default (state: TypePostState = initialState, action): TypePostState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_TYPEPOSTS):
    case REQUEST(ACTION_TYPES.FETCH_TYPEPOST_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TYPEPOST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_TYPEPOST):
    case REQUEST(ACTION_TYPES.UPDATE_TYPEPOST):
    case REQUEST(ACTION_TYPES.DELETE_TYPEPOST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.SEARCH_TYPEPOSTS):
    case FAILURE(ACTION_TYPES.FETCH_TYPEPOST_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TYPEPOST):
    case FAILURE(ACTION_TYPES.CREATE_TYPEPOST):
    case FAILURE(ACTION_TYPES.UPDATE_TYPEPOST):
    case FAILURE(ACTION_TYPES.DELETE_TYPEPOST):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.SEARCH_TYPEPOSTS):
    case SUCCESS(ACTION_TYPES.FETCH_TYPEPOST_LIST): {
      const links = parseHeaderForLinks(action.payload.headers.link);

      return {
        ...state,
        loading: false,
        links,
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links),
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    }
    case SUCCESS(ACTION_TYPES.FETCH_TYPEPOST):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_TYPEPOST):
    case SUCCESS(ACTION_TYPES.UPDATE_TYPEPOST):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_TYPEPOST):
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

const apiUrl = 'api/type-posts';
const apiSearchUrl = 'api/_search/type-posts';

// Actions

export const getSearchEntities: ICrudSearchAction<ITypePost> = (query, page, size, sort) => ({
  type: ACTION_TYPES.SEARCH_TYPEPOSTS,
  payload: axios.get<ITypePost>(`${apiSearchUrl}?query=${query}${sort ? `&page=${page}&size=${size}&sort=${sort}` : ''}`),
});

export const getEntities: ICrudGetAllAction<ITypePost> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_TYPEPOST_LIST,
    payload: axios.get<ITypePost>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<ITypePost> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TYPEPOST,
    payload: axios.get<ITypePost>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ITypePost> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TYPEPOST,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const updateEntity: ICrudPutAction<ITypePost> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TYPEPOST,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITypePost> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TYPEPOST,
    payload: axios.delete(requestUrl),
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
