import dayjs from 'dayjs';

export interface IImage {
  id?: number;
  uuid?: string;
  imageDataContentType?: string;
  imageData?: string;
  nameImage?: string | null;
  extension?: string | null;
  typeFile?: string | null;
  searchField?: string | null;
  role?: string | null;
  createdDate?: string | null;
  modifiedDate?: string | null;
  createdBy?: string | null;
  modifiedBy?: string | null;
  dataSize?: number | null;
  comment?: string | null;
}

export const defaultValue: Readonly<IImage> = {};
