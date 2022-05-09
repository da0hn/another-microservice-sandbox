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

  public async verifyStock(itens: ProductItem[], token: string, transactionid: string, serviceid: string): Promise<IProductStockUpdateResponse> {
    try {
      const headers = {
        Authorization: `${token}`,
        'Content-Type': 'application/json',
        Accept: 'application/json',
        transactionid,
      };
      const url = `${this.productUrl}/products/verify-stock`;

      const request = { products: itens };

      console.info(`Verifying stock ${url}: ${JSON.stringify(request)} | [ transactionid: ${transactionid} | serviceid: ${serviceid} ]`);

      const {
        data,
        status,
      } = await this.httpClient.post<IProductStockUpdateResponse>(
        url,
        request,
        { headers },
      );

      console.info(`Response received from product service ${JSON.stringify({
        data,
        status,
      })} | [ transactionid: ${transactionid} | serviceid: ${serviceid} ]`);

      return data as IProductStockUpdateResponse;

    } catch (error: unknown) {
      console.error((error as Error).message);

      if ( error instanceof AxiosError ) {

        type ErrorResponse = {
          status: number,
          message: string
        };

        if ( !error.response ) {
          throw new GatewayException(`Unable to communicate with product service | [ transactionid: ${transactionid} | serviceid: ${serviceid} ]`);
        }

        const response = error.response?.data as ErrorResponse;
        const status = error.response?.status;

        throw new GatewayException(`An error occour in communication with product service: ${response.message} | [ transactionid: ${transactionid} | serviceid: ${serviceid} ]`, status);
      }

      throw new GatewayException(`Unexpected error occour: ${(error as Error).message} | [ transactionid: ${transactionid} | serviceid: ${serviceid} ]`, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
