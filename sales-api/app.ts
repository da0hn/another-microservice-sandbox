import express, { Request, Response } from 'express';
import { connectInMongoDB } from './src/config/db/mongoDbConfig';
import { constants } from './src/config/constants/constants';

const app = express();

const env = process.env;

const PORT = env.PORT || 8081;

connectInMongoDB();

app.get(`${constants.BASE_URL}/health/status`, (request: Request, response: Response) => {
  return response.status(200).json({
    service: 'sales-api',
    status: 'up',
    httpStatus: 200,
  });
});


app.listen(PORT, () => {
  console.info(`Server started successfully at port ${PORT}`);
});





