import OrderController from './OrderController';
import { orderService } from '../services';


export const orderController = new OrderController(orderService);
