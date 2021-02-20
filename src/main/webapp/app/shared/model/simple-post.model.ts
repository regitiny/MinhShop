import { IPostDetails } from 'app/shared/model/post-details.model';
import { ITypePost } from 'app/shared/model/type-post.model';
import { ITypePostFilter } from 'app/shared/model/type-post-filter.model';

export interface ISimplePost {
  id?: number;
  uuid?: string;
  title?: string;
  price?: number | null;
  salePrice?: number | null;
  percentSale?: number | null;
  imageUrl?: string;
  scores?: number | null;
  simpleContent?: string | null;
  otherInfo?: string | null;
  searchField?: string | null;
  role?: string | null;
  createdDate?: string | null;
  modifiedDate?: string | null;
  createdBy?: string | null;
  modifiedBy?: string | null;
  dataSize?: number | null;
  comment?: string | null;
  postDetails?: IPostDetails;
  typePost?: ITypePost | null;
  typePostFilters?: ITypePostFilter[] | null;
}

export const defaultValue: Readonly<ISimplePost> = {};
