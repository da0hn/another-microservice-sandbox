import amqp, { Channel } from 'amqplib/callback_api';
import { secret } from '../../../config/secrets/secret';
import { RabbitMQ } from '../../../config/constants/constants';

interface ICommand {
  salesId: string,
  itens: {productId: number, quantity: number}[]
}


export function dispatchStockUpdate(data: ICommand) {
  amqp.connect(secret.RABBITMQ_URL, (error, connection) => {
    if ( error ) throw error;

    console.info('Dispatch stock update to queue...');

    connection.createChannel((error, channel: Channel) => {

      if ( error ) throw error;

      const rawData = JSON.stringify(data);

      channel.publish(
        RabbitMQ.PRODUCT_TOPIC,
        RabbitMQ.PRODUCT_STOCK_UPDATE_ROUTING_KEY,
        Buffer.from(rawData),
      );

      console.info(`Dispatched update on stock: ${rawData}`);

    });
  });
}

