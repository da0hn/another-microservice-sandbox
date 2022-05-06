import { Product } from './Product';
import { User } from './User';

export enum Status { APPROVED = 'APPROVED', REJECTED = 'REJECTED'}


export type Order = {
  products: Product[],
  user: User,
  status: Status,
  createdAt: Date,
  updatedAt: Date,
}
