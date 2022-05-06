export enum Status { APPROVED = 'APPROVED', REJECTED = 'REJECTED'}

export type User = {
  id: string,
  name: string,
  email: string
};

export type Product = {
  productId: number,
  quantity: number
};

export type Order = {
  products: Product[],
  user: User,
  status: Status,
  createdAt: Date,
  updatedAt: Date,
}
