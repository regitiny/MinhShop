import dayjs from 'dayjs';
import { ISimplePost } from 'app/shared/model/simple-post.model';

export interface ITypePostFilter {
  id?: number;
  uuid?: string;
  typeFilterName?: string;
  createdDate?: string;
  modifiedDate?: string;
  createdBy?: string;
  modifiedBy?: string;
  simplePosts?: ISimplePost[] | null;
}

export const defaultValue: Readonly<ITypePostFilter> = {};
