import express from 'express';
import * as db from './src/config/db/initialData.js';
import { router } from './src/config/route/index.js';


const app = express();
const env = process.env;
const PORT = env.PORT || 8082;

await db.createInitialData();

app.use(express.json());
app.use(router);

app.listen(PORT, () => {
  console.info(`Server started successfully at port ${ PORT }`);
});

