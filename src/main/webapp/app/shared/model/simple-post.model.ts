import dayjs from 'dayjs';
import { IPostDetails } from 'app/shared/model/post-details.model';
import { ITypePost } from 'app/shared/model/type-post.model';
import { ITypePostFilter } from 'app/shared/model/type-post-filter.model';

export interface ISimplePost {
  id?: number;
  uuid?: string;
  title?: string;
  price?: number;
  salePrice?: number;
  percentSale?: number;
  imageUrl?: string;
  scores?: number;
  simpleContent?: string | null;
  otherInfo?: string;
  role?: string;
  createdDate?: string;
  modifiedDate?: string;
  createdBy?: string;
  modifiedBy?: string;
  dataSize?: number | null;
  comment?: string | null;
  postDetails?: IPostDetails;
  typePost?: ITypePost | null;
  typePostFilters?: ITypePostFilter[] | null;
}

export const defaultValue: Readonly<ISimplePost> = {};
