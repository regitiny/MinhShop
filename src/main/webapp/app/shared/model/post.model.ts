export interface IPost {
  id?: number;
  title?: string;
  price?: number | null;
  salePrice?: number | null;
  percentSale?: number | null;
  imageUrl?: string;
  scores?: number | null;
  simpleContent?: string | null;
  otherInfo?: string | null;
  postDetailsId?: string;
  content?: string | null;
  role?: string | null;
  createdBy?: string | null;
  modifiedBy?: string | null;
  comment?: string | null;
}

export const defaultValue: Readonly<IPost> = {};
