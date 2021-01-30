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
  createdDate?: string;
  modifiedDate?: string;
  createdBy?: string;
  modifiedBy?: string;
  userName?: IUser | null;
}

export const defaultValue: Readonly<IUserOtherInfo> = {};
