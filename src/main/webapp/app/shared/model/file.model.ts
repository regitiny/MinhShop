import dayjs from 'dayjs';

export interface IFile {
  id?: number;
  uuid?: string;
  videoDataContentType?: string;
  videoData?: string;
  nameVideo?: string | null;
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

export const defaultValue: Readonly<IFile> = {
  deleted: false,
};
