import dayjs from 'dayjs';
import { ISimplePost } from 'app/shared/model/simple-post.model';

export interface IPostDetails {
  id?: number;
  uuid?: string;
  postDetailsId?: string;
  content?: string | null;
  searchField?: string | null;
  role?: string | null;
  createdDate?: string | null;
  modifiedDate?: string | null;
  createdBy?: string | null;
  modifiedBy?: string | null;
  dataSize?: number | null;
  comment?: string | null;
  otherData?: string | null;
  simplePost?: ISimplePost | null;
}

export const defaultValue: Readonly<IPostDetails> = {};
