import { OrderService } from '../services/OrderService';
import { Request, Response } from 'express';
import { OrderException } from '../services/OrderException';
import { HttpStatus } from '../../../config/constants/constants';
import { AuthenticationData } from '../../../middlewares/auth/checkToken';
import { Product } from '../model/Order';
import { GatewayException } from '../../products/gateway/GatewayException';


export default class OrderController {
  constructor(private service: OrderService) {
  }

  private static handleError(error: unknown | OrderException | GatewayException, response: Response, transactionid?: string, serviceid?: string): Object {
    if ( error instanceof OrderException ) {

      console.error(`An error occour ${error.message} | [ transactionid: ${transactionid} | serviceid: ${serviceid} ]`);

      const httpStatus = (error as OrderException).httpStatus ?? HttpStatus.INTERNAL_SERVER_ERROR;

      return response.status(httpStatus).json({
        status: httpStatus,
        message: (error as OrderException).message,
      });
    }
    if ( error instanceof GatewayException ) {
      console.error(`An error occour ${error.message} | [ transactionid: ${transactionid} | serviceid: ${serviceid} ]`);

      const httpStatus = (error as GatewayException).httpStatus ?? HttpStatus.INTERNAL_SERVER_ERROR;

      return response.status(httpStatus).json({
        status: httpStatus,
        message: (error as GatewayException).message,
      });
    }
    const message = (error as Error).message;

    console.error(`An error occour ${message} | [ transactionid: ${transactionid} | serviceid: ${serviceid} ]`);

    return response.status(HttpStatus.INTERNAL_SERVER_ERROR).json({
      status: HttpStatus.INTERNAL_SERVER_ERROR,
      message,
    });
  }

  public async findAll(request: Request, response: Response) {
    const { transactionid, serviceid } = request.headers;
    try {
      const orders = await this.service.findAll();

      console.info(`Response ${JSON.stringify(orders)} | [ transactionid: ${transactionid} | serviceid: ${serviceid} ]`);

      return response.status(orders.status).json(orders);
    } catch (e: unknown | OrderException) {
      return OrderController.handleError(e, response, transactionid as string, serviceid as string);
    }
  }

  public async findById(request: Request, response: Response) {
    const { transactionid, serviceid } = request.headers;
    try {
      const { orderId } = request.params;
      const order = await this.service.findById({ orderId });

      console.info(`Response ${JSON.stringify(order)} | [ transactionid: ${transactionid} | serviceid: ${serviceid} ]`);

      return response.status(order.status).json(order);
    } catch (e: unknown | OrderException) {
      return OrderController.handleError(e, response, transactionid as string, serviceid as string);
    }
  }

  public async create(request: Request, response: Response) {

    const { authorization, transactionid, serviceid } = request.headers;

    try {
      const user = (request as AuthenticationData).authUser;

      const order: {itens: Product[]} = request.body;

      const newOrder = await this.service.create({
        products: order.itens,
        user,
        token: authorization!! as string,
        transactionid: transactionid as string,
        serviceid: serviceid as string,
      });

      console.info(`Response ${JSON.stringify(newOrder)} | [ transactionid: ${transactionid} | serviceid: ${serviceid} ]`);

      return response.status(newOrder.status).json(newOrder);
    } catch (e: unknown | OrderException | GatewayException) {
      return OrderController.handleError(e, response, transactionid as string, serviceid as string);
    }
  }

  public async findByProductId(request: Request, response: Response) {
    const { transactionid, serviceid } = request.headers;
    try {

      const productId = Number(request.params.productId);

      const orders = await this.service.findByProductId(productId);

      console.info(`Response ${JSON.stringify(orders)} | [ transactionid: ${transactionid} | serviceid: ${serviceid} ]`);

      return response.status(orders.status).json(orders);
    } catch (e: unknown | OrderException) {
      return OrderController.handleError(e, response, transactionid as string, serviceid as string);
    }
  }
}

