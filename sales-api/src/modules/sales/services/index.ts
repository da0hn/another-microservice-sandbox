import { OrderService } from './OrderService';
import { orderRepository } from '../repositories';
import { productGateway } from '../../products/gateway';


export const orderService = new OrderService(orderRepository, productGateway);
