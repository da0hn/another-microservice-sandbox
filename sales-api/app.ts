import express, { Request, Response } from 'express';
import { connectInMongoDB } from './src/config/db/mongoDbConfig';
import { constants } from './src/config/constants/constants';
import { checkToken } from './src/middlewares/auth/checkToken';
import { connectInRabbitMQ } from './src/config/queue/rabbitConfig';
import { router } from './src/config/route';
import { validateTransactionId } from './src/middlewares/tracing';

const app = express();

const env = process.env;

const PORT = env.PORT || 8081;

(async () => {
  await connectInMongoDB();
  if ( 'container' === env.NODE_ENV ) {
    const THREE_MINUTES = 180000;
    console.info('Waiting for RabbitMQ to start...');
    setTimeout(async () => {
      await connectInRabbitMQ();
    }, THREE_MINUTES);
  } else {
    await connectInRabbitMQ();
  }

})();

app.use(express.json());

app.get(`${constants.BASE_URL}/health/status`, (request: Request, response: Response) => {
  return response.status(200).json({
    service: 'sales-api',
    status: 'up',
    httpStatus: 200,
  });
});

app.use(validateTransactionId);
app.use(checkToken);
app.use(router);


app.listen(PORT, () => {
  console.info(`Server started successfully at port ${PORT}`);
});





