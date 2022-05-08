import { Request, Response, Router } from 'express';
import { OrderRepository } from '../repositories/OrderRepository';
import OrderService from '../services/OrderService';
import OrderController from './OrderController';

export const orderRoute = Router();

const orderRepository = new OrderRepository();
const orderService = new OrderService(orderRepository);
const orderController = new OrderController(orderService);

orderRoute.get(`/:orderId`, (request: Request, response: Response) => orderController.findById(request, response));
orderRoute.get(`/`, (request: Request, response: Response) => orderController.findAll(request, response));
orderRoute.post(`/`, (request: Request, response: Response) => orderController.create(request, response));





