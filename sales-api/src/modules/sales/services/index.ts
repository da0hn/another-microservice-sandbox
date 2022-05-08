import { OrderService } from './OrderService';
import { orderRepository } from '../repositories';


export const orderService = new OrderService(orderRepository);
