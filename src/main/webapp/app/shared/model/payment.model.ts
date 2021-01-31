import dayjs from 'dayjs';
import { IBill } from 'app/shared/model/bill.model';

export interface IPayment {
  id?: number;
  uuid?: string;
  status?: string | null;
  role?: string;
  createdDate?: string;
  modifiedDate?: string;
  createdBy?: string;
  modifiedBy?: string;
  billId?: IBill | null;
}

export const defaultValue: Readonly<IPayment> = {};
