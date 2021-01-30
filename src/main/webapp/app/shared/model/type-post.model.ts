import dayjs from 'dayjs';

export interface ITypePost {
  id?: number;
  uuid?: string;
  typeName?: string;
  createdDate?: string;
  modifiedDate?: string;
  createdBy?: string;
  modifiedBy?: string;
}

export const defaultValue: Readonly<ITypePost> = {};
