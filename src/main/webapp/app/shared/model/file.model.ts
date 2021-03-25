export interface IFile
{
  id?: number;
  uuid?: string;
  fileDataContentType?: string;
  fileData?: string;
  nameFile?: string | null;
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

export const defaultValue: Readonly<IFile> = {};
