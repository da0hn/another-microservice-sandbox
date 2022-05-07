import amqp, { Channel } from 'amqplib/callback_api';
import { RabbitMQ } from '../../../config/constants/constants';
import { secret } from '../../../config/secrets/secret';

export function listenSalesConfirmation() {
  amqp.connect(secret.RABBITMQ_URL, (error, connection) => {
    if ( error ) throw error;
    console.info('Listening on Sales Confirmation Queue...');
    connection.createChannel((error, channel: Channel) => {
      if ( error ) throw error;
      channel.consume(RabbitMQ.SALES_CONFIRMATION_QUEUE, (message) => {
        console.info(`Receive an message from queue: \n${message?.content?.toString()}`);
      }, { noAck: true });
    });
  });
}
