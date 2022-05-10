import { OrderRepository } from '../repositories/OrderRepository';
import { OrderException } from './OrderException';
import { HttpStatus } from '../../../config/constants/constants';
import { Order, Product, Status, User } from '../model/Order';
import OrderSchema from '../repositories/OrderSchema';
import { dispatchStockUpdate, IUpdateProductStockRequest } from '../queue/productStockUpdateDispatcher';
import { AuthenticatedUser } from '../../../middlewares/auth/AuthenticatedUser';
import { ProductGateway, ProductItem } from '../../products/gateway/ProductGateway';


export class OrderService {

  constructor(private repository: OrderRepository, private productGateway: ProductGateway) {
  }

  private static validateOptionalOrder(maybeOrder: null | any, orderId: string): void {
    if ( !maybeOrder ) {
      throw new OrderException(`The order ${orderId} not found`, HttpStatus.NOT_FOUND);
    }
  }

  private static validateOrderId(orderId: string): void {
    if ( !orderId ) {
      throw new OrderException(`The Order id must be informed`, HttpStatus.BAD_REQUEST);
    }
  }

  private static validateOrderData(order: Order): void {
    if ( !order ) {
      throw new OrderException(`Order data must be informed`, HttpStatus.BAD_REQUEST);
    }
    if ( !order.user ) {
      throw new OrderException(`User data must be informed`, HttpStatus.BAD_REQUEST);
    }
    if ( !order.products || order.products.length === 0 ) {
      throw new OrderException(`Products of order must be informed`, HttpStatus.BAD_REQUEST);
    }
  }

  async findById(request: {orderId: string}): Promise<OrderResponse> {
    const orderId = request.orderId;

    OrderService.validateOrderId(orderId);

    const maybeOrder = await this.repository.findById(orderId);

    OrderService.validateOptionalOrder(maybeOrder, orderId);

    return {
      status: HttpStatus.SUCCESS,
      order: maybeOrder,
    } as OrderResponse;
  }

  async findAll() {
    const orders = await this.repository.findAll();
    return {
      status: HttpStatus.SUCCESS,
      orders,
    };
  }

  async create(request: ICreateOrderRequest) {

    const order = new OrderSchema({
      products: request.products,
      user: request.user,
      status: Status.PENDING,
      createdAt: new Date(),
      updatedAt: new Date(),
      transactionId: request.transactionid,
      serviceId: request.serviceid,
    });

    OrderService.validateOrderData(order);

    const itens = order.products.map((product) => {
      return {
        productId: product.productId,
        quantity: product.quantity,
      };
    });

    await this.validateProductStock(itens, request.token, request.transactionid, request.serviceid);

    const newOrder: Order = await this.repository.save(order);

    console.info(`Order successfully created ${JSON.stringify(newOrder)} | [ transactionid: ${request.transactionid} | serviceid: ${request.serviceid} ]`);

    const stockUpdate: IUpdateProductStockRequest = {
      data: {
        salesId: newOrder.id,
        itens,
      },
      headers: {
        transactionid: request.transactionid,
        serviceid: request.serviceid,
      },
    };

    dispatchStockUpdate(stockUpdate);

    return {
      status: HttpStatus.SUCCESS,
      order: newOrder as Order,
    };
  }

  public async updateOrder(request: IUpdateOrderRequest) {
    try {

      if ( !request.status || !request.salesId ) {
        console.warn(`The update order request is invalid ${JSON.stringify(request)} | [ transactionid: ${request.transactionid} ]`);
        return;
      }

      const maybeOrder = await this.repository.findById(request.salesId);

      OrderService.validateOptionalOrder(maybeOrder, request.salesId);

      if ( maybeOrder!!.status === request.status ) {
        console.warn(`The update order request has not change the status ${JSON.stringify(request)} | [ transactionid: ${request.transactionid} ]`);
        return;
      }

      maybeOrder!!.status = request.status;
      maybeOrder!!.updatedAt = new Date();

      await this.repository.save(maybeOrder!!);

      console.info(`The order has been updated successfully ${JSON.stringify(maybeOrder!!)} | [ transactionid: ${request.transactionid} ]`);

    } catch (error: any) {
      console.error(`Could not parse order message from queue: '${error.message}' | [ transactionid: ${request.transactionid} ]`);
    }
  }

  public async findByProductId(productId: number) {

    if ( !productId ) {
      throw new OrderException(`The product id must be informed`, HttpStatus.BAD_REQUEST);
    }

    const orders = await this.repository.findByProductId(productId);

    const status = orders.length > 0 ? HttpStatus.SUCCESS : HttpStatus.NO_CONTENT;

    return {
      status,
      salesId: orders.map((order) => order.id as string),
    };
  }

  private async validateProductStock(itens: ProductItem[], token: string, transactionid: string, serviceid: string): Promise<void> {

    const response = await this.productGateway.verifyStock(itens, token, transactionid, serviceid);

    if ( !response.valid ) {

      const productsIds = response.productsOutOfStock.map(product => product.productId).join(', ');

      throw new OrderException(
        `The products are out of stock | ids: [ ${productsIds} ] | [ transactionid: ${transactionid} | serviceid: ${serviceid} ]`,
        HttpStatus.BAD_REQUEST,
      );
    }

  }


}

type OrderResponse = {
  status: number,
  order: {
    id: string,
    products: Product[],
    user: User,
    status: Status,
    createdAt: Date,
    updatedAt: Date,
    transactionId: string,
    serviceId: string,
  }
}

export interface IUpdateOrderRequest {
  salesId: string,
  status: Status,
  transactionid: string,
}

export interface ICreateOrderRequest {
  user: User | AuthenticatedUser;
  products: Product[];
  token: string;
  transactionid: string;
  serviceid: string;
}
