import { secret } from '../secrets/secret';
import { connect, connection } from 'mongoose';
import { createInitialData } from './initialData';

export function connectInMongoDB() {
  connect(secret.MONGO_DB_URL);
  connection.on('connected', async () => {
    console.info(`The application connected to MongoDB successfully!`);
    await createInitialData();
  });
  connection.on('error', () => {
    console.error(`Error trying connect on MongoDB`);
  });
}
