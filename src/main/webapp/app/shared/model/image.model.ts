import dayjs from 'dayjs';

export interface IImage {
  id?: number;
  uuid?: string;
  imageDataContentType?: string;
  imageData?: string;
  nameImage?: string | null;
  extension?: string | null;
  typeFile?: string | null;
  role?: string;
  createdDate?: string;
  modifiedDate?: string;
  createdBy?: string;
  modifiedBy?: string;
  dataSize?: number | null;
  comment?: string | null;
  deleted?: boolean | null;
}

export const defaultValue: Readonly<IImage> = {
  deleted: false,
};
