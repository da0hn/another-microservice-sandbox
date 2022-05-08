import { OrderRepository } from '../repositories/OrderRepository';
import { OrderException } from './OrderException';
import { HttpStatus } from '../../../config/constants/constants';
import { Order, Product, Status, User } from '../model/Order';
import OrderSchema from '../repositories/OrderSchema';
import { dispatchStockUpdate } from '../queue/productStockUpdateDispatcher';
import { AuthenticatedUser } from '../../../middlewares/auth/AuthenticatedUser';

type OrderResponse = {
  status: number,
  order: {
    id: string,
    products: Product[],
    user: User,
    status: Status,
    createdAt: Date,
    updatedAt: Date,
  }
}

export class OrderService {

  constructor(private repository: OrderRepository) {
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

  async create(data: ICreateOrderRequest) {

    const order = new OrderSchema({
      products: data.products,
      user: data.user,
      status: Status.PENDING,
      createdAt: new Date(),
      updatedAt: new Date(),
    });

    OrderService.validateOrderData(order);

    const newOrder: Order = await this.repository.save(order);

    dispatchStockUpdate({
      salesId: newOrder.id,
      itens: newOrder.products.map((product) => {
        return {
          productId: product.productId,
          quantity: product.quantity,
        };
      }),
    });

    return {
      status: HttpStatus.SUCCESS,
      order: newOrder as Order,
    };
  }

  public async updateOrder(request: IUpdateOrderRequest) {
    try {

      if ( !request.status || !request.salesId ) {
        console.warn(`The update order request is invalid ${request}`);
        return;
      }

      const maybeOrder = await this.repository.findById(request.salesId);

      OrderService.validateOptionalOrder(maybeOrder, request.salesId);

      if ( maybeOrder!!.status === request.status ) {
        console.warn(`The update order request has not change the status ${request}`);
        return;
      }

      maybeOrder!!.status = request.status;
      await this.repository.save(maybeOrder!!);

    } catch (error: any) {
      console.error(`Could not parse order message from queue: '${error.message}'`);
    }
  }
}

export interface IUpdateOrderRequest {
  salesId: string,
  status: Status
}

export interface ICreateOrderRequest {
  user: User | AuthenticatedUser;
  products: Product[];
}
