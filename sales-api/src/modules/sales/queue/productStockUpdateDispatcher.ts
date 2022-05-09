import amqp, { Channel } from 'amqplib/callback_api';
import { secret } from '../../../config/secrets/secret';
import { RabbitMQ } from '../../../config/constants/constants';

export interface IUpdateProductStockRequest {
  data: {
    salesId: string,
    itens: {productId: number, quantity: number}[]
  },
  headers: {
    transactionid: string,
    serviceid: string
  }
}


export function dispatchStockUpdate(request: IUpdateProductStockRequest) {
  amqp.connect(secret.RABBITMQ_URL, (error, connection) => {
    if ( error ) throw error;

    const { headers } = request;

    console.info(`Dispatch stock update to queue ${JSON.stringify(request.data)} | [ transactionid: ${headers.transactionid} | serviceid: ${headers.serviceid} ]`);

    connection.createChannel((error, channel: Channel) => {

      if ( error ) throw error;

      const rawData = JSON.stringify(request.data);

      channel.publish(
        RabbitMQ.PRODUCT_TOPIC,
        RabbitMQ.PRODUCT_STOCK_UPDATE_ROUTING_KEY,
        Buffer.from(rawData),
      );

      console.info(`Dispatched update on stock: ${rawData} | [ transactionid: ${headers.transactionid} | serviceid: ${headers.serviceid} ]`);

    });
  });
}

