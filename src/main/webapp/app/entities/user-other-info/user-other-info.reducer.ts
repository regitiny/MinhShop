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

import { IUserOtherInfo, defaultValue } from 'app/shared/model/user-other-info.model';

export const ACTION_TYPES = {
  SEARCH_USEROTHERINFOS: 'userOtherInfo/SEARCH_USEROTHERINFOS',
  FETCH_USEROTHERINFO_LIST: 'userOtherInfo/FETCH_USEROTHERINFO_LIST',
  FETCH_USEROTHERINFO: 'userOtherInfo/FETCH_USEROTHERINFO',
  CREATE_USEROTHERINFO: 'userOtherInfo/CREATE_USEROTHERINFO',
  UPDATE_USEROTHERINFO: 'userOtherInfo/UPDATE_USEROTHERINFO',
  DELETE_USEROTHERINFO: 'userOtherInfo/DELETE_USEROTHERINFO',
  RESET: 'userOtherInfo/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IUserOtherInfo>,
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type UserOtherInfoState = Readonly<typeof initialState>;

// Reducer

export default (state: UserOtherInfoState = initialState, action): UserOtherInfoState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_USEROTHERINFOS):
    case REQUEST(ACTION_TYPES.FETCH_USEROTHERINFO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_USEROTHERINFO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_USEROTHERINFO):
    case REQUEST(ACTION_TYPES.UPDATE_USEROTHERINFO):
    case REQUEST(ACTION_TYPES.DELETE_USEROTHERINFO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.SEARCH_USEROTHERINFOS):
    case FAILURE(ACTION_TYPES.FETCH_USEROTHERINFO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_USEROTHERINFO):
    case FAILURE(ACTION_TYPES.CREATE_USEROTHERINFO):
    case FAILURE(ACTION_TYPES.UPDATE_USEROTHERINFO):
    case FAILURE(ACTION_TYPES.DELETE_USEROTHERINFO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.SEARCH_USEROTHERINFOS):
    case SUCCESS(ACTION_TYPES.FETCH_USEROTHERINFO_LIST): {
      const links = parseHeaderForLinks(action.payload.headers.link);

      return {
        ...state,
        loading: false,
        links,
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links),
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    }
    case SUCCESS(ACTION_TYPES.FETCH_USEROTHERINFO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_USEROTHERINFO):
    case SUCCESS(ACTION_TYPES.UPDATE_USEROTHERINFO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_USEROTHERINFO):
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

const apiUrl = 'api/user-other-infos';
const apiSearchUrl = 'api/_search/user-other-infos';

// Actions

export const getSearchEntities: ICrudSearchAction<IUserOtherInfo> = (query, page, size, sort) => ({
  type: ACTION_TYPES.SEARCH_USEROTHERINFOS,
  payload: axios.get<IUserOtherInfo>(`${apiSearchUrl}?query=${query}${sort ? `&page=${page}&size=${size}&sort=${sort}` : ''}`),
});

export const getEntities: ICrudGetAllAction<IUserOtherInfo> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_USEROTHERINFO_LIST,
    payload: axios.get<IUserOtherInfo>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IUserOtherInfo> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_USEROTHERINFO,
    payload: axios.get<IUserOtherInfo>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IUserOtherInfo> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_USEROTHERINFO,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const updateEntity: ICrudPutAction<IUserOtherInfo> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_USEROTHERINFO,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IUserOtherInfo> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_USEROTHERINFO,
    payload: axios.delete(requestUrl),
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
