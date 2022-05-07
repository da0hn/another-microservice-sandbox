import amqp, { Connection } from 'amqplib/callback_api';
import { secret } from '../secrets/secret';
import { RabbitMQ } from '../constants/constants';

const TWO_SECONDS = 2000;


export async function connectInRabbitMQ() {

  amqp.connect(secret.RABBITMQ_URL, { timeout: 180000 }, (error, connection) => {
    if ( error ) {
      throw error;
    }

    console.info('Starting RabbitMQ...');

    createQueue(
      connection,
      RabbitMQ.PRODUCT_STOCK_UPDATE_QUEUE,
      RabbitMQ.PRODUCT_STOCK_UPDATE_ROUTING_KEY,
      RabbitMQ.PRODUCT_TOPIC,
    );

    createQueue(
      connection,
      RabbitMQ.SALES_CONFIRMATION_QUEUE,
      RabbitMQ.SALES_CONFIRMATION_ROUTING_KEY,
      RabbitMQ.PRODUCT_TOPIC,
    );

    console.info('Queues and Topics were defined');

    setTimeout(() => {
      connection.close();
    }, TWO_SECONDS);


  });

  function createQueue(connection: Connection, queue: string, routingKey: string, topic: string) {
    connection.createChannel((error, channel) => {
      if ( error ) throw error;
      channel.assertExchange(topic, 'topic', { durable: true });
      channel.assertQueue(queue, { durable: true });
      channel.bindQueue(queue, topic, routingKey);
    });
  }
}



