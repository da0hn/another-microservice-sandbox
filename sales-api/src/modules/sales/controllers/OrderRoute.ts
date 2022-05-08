import { Request, Response, Router } from 'express';
import { orderController } from './index';

export const orderRoute = Router();


orderRoute.get(`/:orderId`, (request: Request, response: Response) => orderController.findById(request, response));
orderRoute.get(`/`, (request: Request, response: Response) => orderController.findAll(request, response));
orderRoute.post(`/`, (request: Request, response: Response) => orderController.create(request, response));





