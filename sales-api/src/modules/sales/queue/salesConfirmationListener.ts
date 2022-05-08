import amqp, { Channel } from 'amqplib/callback_api';
import { RabbitMQ } from '../../../config/constants/constants';
import { secret } from '../../../config/secrets/secret';
import { IUpdateOrderRequest, OrderService } from '../services/OrderService';

export function listenSalesConfirmation(service: OrderService) {

  amqp.connect(secret.RABBITMQ_URL, (error, connection) => {
    if ( error ) throw error;
    console.info('Listening on Sales Confirmation Queue...');
    connection.createChannel(async (error, channel: Channel) => {
      if ( error ) throw error;
      channel.consume(RabbitMQ.SALES_CONFIRMATION_QUEUE, async (message) => {
        console.info(`Receive an message from queue: \n${message?.content?.toString()}`);

        if ( !message || !message?.content ) {
          console.error(`Received message is invalid`);
          return;
        }

        const jsonParsed = JSON.parse(message.content.toString()) as IUpdateOrderRequest;

        await service.updateOrder(jsonParsed);
      }, { noAck: true });
    });
  });
}
