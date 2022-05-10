import { Document } from 'mongoose';

export enum Status { APPROVED = 'APPROVED', REJECTED = 'REJECTED', PENDING = 'PENDING'}

export interface User extends Document {
  id: string,
  name: string,
  email: string
}

export interface Product extends Document {
  productId: number,
  quantity: number
}

export interface Order extends Document {
  products: Product[],
  user: User,
  status: Status,
  createdAt: Date,
  updatedAt: Date,
  transactionId: string,
  serviceId: string,
}
