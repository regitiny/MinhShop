import dayjs from 'dayjs';
import { IBill } from 'app/shared/model/bill.model';

export interface IPayment {
  id?: number;
  uuid?: string;
  status?: string | null;
  searchField?: string | null;
  role?: string | null;
  createdDate?: string | null;
  modifiedDate?: string | null;
  createdBy?: string | null;
  modifiedBy?: string | null;
  dataSize?: number | null;
  comment?: string | null;
  billId?: IBill | null;
}

export const defaultValue: Readonly<IPayment> = {};
