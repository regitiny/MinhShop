export interface ITypePost {
  id?: number;
  uuid?: string;
  typeName?: string;
  searchField?: string | null;
  role?: string | null;
  createdDate?: string | null;
  modifiedDate?: string | null;
  createdBy?: string | null;
  modifiedBy?: string | null;
  dataSize?: number | null;
  comment?: string | null;
}

export const defaultValue: Readonly<ITypePost> = {};
