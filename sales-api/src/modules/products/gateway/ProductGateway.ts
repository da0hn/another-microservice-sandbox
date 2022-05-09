import { Axios, AxiosError } from 'axios';
import { GatewayException } from './GatewayException';
import { HttpStatus } from '../../../config/constants/constants';

export type ProductItem = {quantity: number; productId: number};
export type IProductStockUpdateRequest = {itens: ProductItem[]; salesId: any};

interface IProductStockUpdateResponse {
  valid: boolean,
  productsOutOfStock: ProductItem[]
}

export class ProductGateway {

  constructor(private httpClient: Axios, private productUrl: string) {
  }

  public async verifyStock(itens: ProductItem[], token: string): Promise<IProductStockUpdateResponse> {
    try {
      const headers = {
        Authorization: `${token}`,
        'Content-Type': 'application/json',
        Accept: 'application/json',
      };
      const url = `${this.productUrl}/products/verify-stock`;

      const request = { products: itens };

      console.info(`Verifying stock ${url}: \n${JSON.stringify(request, null, 4)}`);

      const {
        data,
        status,
      } = await this.httpClient.post<IProductStockUpdateResponse>(
        url,
        request,
        { headers },
      );

      console.info(`Response received from product service ${JSON.stringify({ data, status }, null, 4)}`);

      return data as IProductStockUpdateResponse;

    } catch (error: unknown) {
      console.error((error as Error).message);

      if ( error instanceof AxiosError ) {

        type ErrorResponse = {
          status: number,
          message: string
        };

        if ( !error.response ) {
          throw new GatewayException(`Unable to communicate with product service`);
        }

        const response = error.response?.data as ErrorResponse;
        const status = error.response?.status;

        throw new GatewayException(`An error occour in communication with product service: ${response.message}`, status);
      }

      throw new GatewayException(`Unexpected error occour: ${(error as Error).message}`, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
