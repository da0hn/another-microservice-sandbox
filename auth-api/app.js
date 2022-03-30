import express from 'express';
import * as db from './src/config/db/initialData.js';


const app = express();
const env = process.env;
const PORT = env.PORT || 8082;

await db.createInitialData();

app.get('/api/authentication/status', (request, response) => {
  return response.json({
    service: 'auth-api',
    status: 'up',
    httpStatus: 200,
  })
})

app.listen(PORT, () => {
  console.info(`Server started successfully at port ${ PORT }`)
})

