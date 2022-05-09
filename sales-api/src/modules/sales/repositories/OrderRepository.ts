import { Order } from '../model/Order';
import OrderDocument from './OrderSchema';

export class OrderRepository {

  async findById(id: string): Promise<Order | null> {
    const maybeOrder = await OrderDocument.findById(id);
    if ( !maybeOrder ) {
      return null;
    }
    return maybeOrder as Order;
  }

  async save(order: Order): Promise<Order> {
    return await OrderDocument.create(order);
  }

  async findAll(): Promise<Order[]> {
    const orders = await OrderDocument.find();
    return orders as Order[];
  }

  async findByProductId(productId: number): Promise<Order[]> {
    const orders = await OrderDocument.find({ 'products.productId': productId });
    return orders as Order[];
  }
}

