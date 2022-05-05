import express from 'express';
import { connectInMongoDB } from './src/config/db/mongoDbConfig';


const app = express();

const env = process.env;

const PORT = env.PORT || 8081;

connectInMongoDB();

app.get('/api/sales/status', (request, response) => {
  return response.status(200).json({
    service: 'sales-api',
    status: 'up',
    httpStatus: 200,
  });
});


app.listen(PORT, () => {
  console.info(`Server started successfully at port ${PORT}`);
});





