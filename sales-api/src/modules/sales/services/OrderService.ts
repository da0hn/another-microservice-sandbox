import { OrderRepository } from '../repositories/OrderRepository';
import { OrderException } from './OrderException';
import { HttpStatus } from '../../../config/constants/constants';
import { Order, Product, Status, User } from '../model/Order';
import OrderSchema from '../repositories/OrderSchema';
import { dispatchStockUpdate } from '../queue/productStockUpdateDispatcher';
import { AuthenticatedUser } from '../../../middlewares/auth/AuthenticatedUser';
import { IProductStockUpdateRequest, ProductGateway, ProductItem } from '../../products/gateway/ProductGateway';


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
    });

    OrderService.validateOrderData(order);

    const itens = order.products.map((product) => {
      return {
        productId: product.productId,
        quantity: product.quantity,
      };
    });

    await this.validateProductStock(itens, request.token);

    const newOrder: Order = await this.repository.save(order);

    const stockUpdate: IProductStockUpdateRequest = {
      salesId: newOrder.id,
      itens,
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
        console.warn(`The update order request is invalid ${JSON.stringify(request)}`);
        return;
      }

      const maybeOrder = await this.repository.findById(request.salesId);

      OrderService.validateOptionalOrder(maybeOrder, request.salesId);

      if ( maybeOrder!!.status === request.status ) {
        console.warn(`The update order request has not change the status ${JSON.stringify(request)}`);
        return;
      }

      maybeOrder!!.status = request.status;
      maybeOrder!!.updatedAt = new Date();

      await this.repository.save(maybeOrder!!);

      console.info(`The order has been updated successfully ${JSON.stringify(maybeOrder!!)}`);

    } catch (error: any) {
      console.error(`Could not parse order message from queue: '${error.message}'`);
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

  private async validateProductStock(itens: ProductItem[], token: string): Promise<void> {

    const response = await this.productGateway.verifyStock(itens, token);

    if ( !response.valid ) {
      // TODO: change this message
      throw new OrderException(`The products are out of stock: \n${JSON.stringify(response.productsOutOfStock, null, 2)}`, HttpStatus.BAD_REQUEST);
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
  }
}

export interface IUpdateOrderRequest {
  salesId: string,
  status: Status
}

export interface ICreateOrderRequest {
  user: User | AuthenticatedUser;
  products: Product[];
  token: string;
}
