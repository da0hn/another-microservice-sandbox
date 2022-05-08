import { OrderService } from '../services/OrderService';
import { Request, Response } from 'express';
import { OrderException } from '../services/OrderException';
import { HttpStatus } from '../../../config/constants/constants';
import { AuthenticationData } from '../../../middlewares/auth/checkToken';
import { Product } from '../model/Order';


export default class OrderController {
  constructor(private service: OrderService) {
  }

  private static handleError(error: unknown | OrderException, response: Response): Object {
    if ( error as OrderException ) {
      const httpStatus = (error as OrderException).httpStatus;
      return response.status(httpStatus).json({
        status: httpStatus,
        message: (error as OrderException).message,
      });
    }
    return response.status(HttpStatus.INTERNAL_SERVER_ERROR).json({
      status: HttpStatus.INTERNAL_SERVER_ERROR,
      message: (error as Error).message,
    });
  }

  public async findAll(request: Request, response: Response) {
    try {
      const orders = await this.service.findAll();
      return response.status(orders.status).json(orders);
    } catch (e: unknown | OrderException) {
      return OrderController.handleError(e, response);
    }
  }

  public async findById(request: Request, response: Response) {
    try {
      const { orderId } = request.params;
      const order = await this.service.findById({ orderId });
      return response.status(order.status).json(order);
    } catch (e: unknown | OrderException) {
      return OrderController.handleError(e, response);
    }
  }

  public async create(request: Request, response: Response) {
    try {
      const user = (request as AuthenticationData).authUser;

      const order: {itens: Product[]} = request.body;

      const newOrder = await this.service.create({ products: order.itens, user });
      return response.status(newOrder.status).json(newOrder);
    } catch (e: unknown | OrderException) {
      return OrderController.handleError(e, response);
    }
  }
}

