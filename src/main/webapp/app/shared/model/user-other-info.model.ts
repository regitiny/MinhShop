import dayjs from 'dayjs';
import { IUser } from 'app/shared/model/user.model';

export interface IUserOtherInfo {
  id?: number;
  uuid?: string;
  phoneNumber?: string | null;
  email?: string | null;
  wardCode?: string | null;
  distCode?: string | null;
  cityCode?: string | null;
  addressDetails?: string | null;
  dateOfBirth?: string | null;
  otherInfo?: string | null;
  searchField?: string | null;
  role?: string | null;
  createdDate?: string | null;
  modifiedDate?: string | null;
  createdBy?: string | null;
  modifiedBy?: string | null;
  dataSize?: number | null;
  comment?: string | null;
  userName?: IUser | null;
}

export const defaultValue: Readonly<IUserOtherInfo> = {};
