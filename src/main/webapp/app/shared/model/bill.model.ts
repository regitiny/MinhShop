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
  product?: string | null;
  comment?: string | null;
  searchField?: string | null;
  role?: string | null;
  createdDate?: string | null;
  modifiedDate?: string | null;
  createdBy?: string | null;
  modifiedBy?: string | null;
  dataSize?: number | null;
  userOtherInfo?: IUserOtherInfo | null;
  payment?: IPayment | null;
}

export const defaultValue: Readonly<IBill> = {};
