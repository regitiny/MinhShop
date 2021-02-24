import {ISimplePost} from 'app/shared/model/simple-post.model';

export interface ITypePostFilter
{
  id?: number;
  uuid?: string;
  typeFilterName?: string;
  searchField?: string | null;
  role?: string | null;
  createdDate?: string | null;
  modifiedDate?: string | null;
  createdBy?: string | null;
  modifiedBy?: string | null;
  dataSize?: number | null;
  comment?: string | null;
  simplePosts?: ISimplePost[] | null;
}

export const defaultValue: Readonly<ITypePostFilter> = {};
