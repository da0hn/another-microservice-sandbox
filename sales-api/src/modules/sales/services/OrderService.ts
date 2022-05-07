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

export default class OrderService {

  constructor(private repository: OrderRepository) {
  }

  private static validateOptionalOrder(maybeOrder: null | any, orderId: string): void {
    if ( !maybeOrder ) {
      throw new OrderException(HttpStatus.NOT_FOUND, `The order ${orderId} not found`);
    }
  }

  private static validateOrderId(orderId: string): void {
    if ( !orderId ) {
      throw new OrderException(HttpStatus.BAD_REQUEST, `The Order id must be informed`);
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


  async create(data: ICreateOrderRequest) {

    const order = new OrderSchema({
      products: data.products,
      user: data.user,
      status: Status.PENDING,
      createdAt: new Date(),
      updatedAt: new Date(),
    });

    this.validateOrderData(order);

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

  private validateOrderData(order: Order): void {
    if ( !order ) {
      throw new OrderException(HttpStatus.BAD_REQUEST, `Order data must be informed`);
    }
    if ( !order.user ) {
      throw new OrderException(HttpStatus.BAD_REQUEST, `User data must be informed`);
    }
    if ( !order.products || order.products.length === 0 ) {
      throw new OrderException(HttpStatus.BAD_REQUEST, `Products of order must be informed`);
    }

  }
}

interface ICreateOrderRequest {
  user: User | AuthenticatedUser;
  products: Product[];
}
