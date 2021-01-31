import dayjs from 'dayjs';
import { IUserOtherInfo } from 'app/shared/model/user-other-info.model';
import { IPayment } from 'app/shared/model/payment.model';

export interface IBill {
  id?: number;
  uuid?: string;
  billId?: string;
  phoneNumber?: string;
  email?: string | null;
  addressDetails?: string | null;
  addressCode?: string | null;
  comment?: string | null;
  role?: string;
  createdDate?: string;
  modifiedDate?: string;
  createdBy?: string;
  modifiedBy?: string;
  userOtherInfo?: IUserOtherInfo | null;
  payment?: IPayment | null;
}

export const defaultValue: Readonly<IBill> = {};
