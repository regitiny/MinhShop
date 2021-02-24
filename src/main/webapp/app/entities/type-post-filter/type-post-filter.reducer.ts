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

import {defaultValue, ITypePostFilter} from 'app/shared/model/type-post-filter.model';

export const ACTION_TYPES = {
  SEARCH_TYPEPOSTFILTERS: 'typePostFilter/SEARCH_TYPEPOSTFILTERS',
  FETCH_TYPEPOSTFILTER_LIST: 'typePostFilter/FETCH_TYPEPOSTFILTER_LIST',
  FETCH_TYPEPOSTFILTER: 'typePostFilter/FETCH_TYPEPOSTFILTER',
  CREATE_TYPEPOSTFILTER: 'typePostFilter/CREATE_TYPEPOSTFILTER',
  UPDATE_TYPEPOSTFILTER: 'typePostFilter/UPDATE_TYPEPOSTFILTER',
  DELETE_TYPEPOSTFILTER: 'typePostFilter/DELETE_TYPEPOSTFILTER',
  SET_BLOB: 'typePostFilter/SET_BLOB',
  RESET: 'typePostFilter/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITypePostFilter>,
  entity: defaultValue,
  links: {next: 0},
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type TypePostFilterState = Readonly<typeof initialState>;

// Reducer

export default (state: TypePostFilterState = initialState, action): TypePostFilterState =>
{
  switch (action.type)
  {
    case REQUEST(ACTION_TYPES.SEARCH_TYPEPOSTFILTERS):
    case REQUEST(ACTION_TYPES.FETCH_TYPEPOSTFILTER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TYPEPOSTFILTER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_TYPEPOSTFILTER):
    case REQUEST(ACTION_TYPES.UPDATE_TYPEPOSTFILTER):
    case REQUEST(ACTION_TYPES.DELETE_TYPEPOSTFILTER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.SEARCH_TYPEPOSTFILTERS):
    case FAILURE(ACTION_TYPES.FETCH_TYPEPOSTFILTER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TYPEPOSTFILTER):
    case FAILURE(ACTION_TYPES.CREATE_TYPEPOSTFILTER):
    case FAILURE(ACTION_TYPES.UPDATE_TYPEPOSTFILTER):
    case FAILURE(ACTION_TYPES.DELETE_TYPEPOSTFILTER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.SEARCH_TYPEPOSTFILTERS):
    case SUCCESS(ACTION_TYPES.FETCH_TYPEPOSTFILTER_LIST):
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
    case SUCCESS(ACTION_TYPES.FETCH_TYPEPOSTFILTER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_TYPEPOSTFILTER):
    case SUCCESS(ACTION_TYPES.UPDATE_TYPEPOSTFILTER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_TYPEPOSTFILTER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.SET_BLOB:
    {
      const {name, data, contentType} = action.payload;
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

const apiUrl = 'api/type-post-filters';
const apiSearchUrl = 'api/_search/type-post-filters';

// Actions

export const getSearchEntities: ICrudSearchAction<ITypePostFilter> = (query, page, size, sort) => ({
  type: ACTION_TYPES.SEARCH_TYPEPOSTFILTERS,
  payload: axios.get<ITypePostFilter>(`${apiSearchUrl}?query=${query}${sort ? `&page=${page}&size=${size}&sort=${sort}` : ''}`),
});

export const getEntities: ICrudGetAllAction<ITypePostFilter> = (page, size, sort) =>
{
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_TYPEPOSTFILTER_LIST,
    payload: axios.get<ITypePostFilter>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<ITypePostFilter> = id =>
{
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TYPEPOSTFILTER,
    payload: axios.get<ITypePostFilter>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ITypePostFilter> = entity => async dispatch =>
{
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TYPEPOSTFILTER,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const updateEntity: ICrudPutAction<ITypePostFilter> = entity => async dispatch =>
{
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TYPEPOSTFILTER,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITypePostFilter> = id => async dispatch =>
{
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TYPEPOSTFILTER,
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
