import { Request, Response, Router } from 'express';
import { orderController } from './index';
import { logging } from '../../../middlewares/logging';

export const productRoute = Router();
productRoute.get('/:productId/orders', (request: Request, response: Response) => orderController.findByProductId(request, response));

export const orderRoute = Router();


orderRoute.get(`/:orderId`, logging, (request: Request, response: Response) => orderController.findById(request, response));
orderRoute.get(`/`, logging, (request: Request, response: Response) => orderController.findAll(request, response));
orderRoute.post(`/`, logging, (request: Request, response: Response) => orderController.create(request, response));





