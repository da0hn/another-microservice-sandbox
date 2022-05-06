import { constants } from '../secrets/constants';
import { connect, connection } from 'mongoose';
import { createInitialData } from './initialData';

export function connectInMongoDB() {
  connect(constants.MONGO_DB_URL);
  connection.on('connected', async () => {
    console.info(`The application connected to MongoDB successfully!`);
    await createInitialData();
    console.info(`Initial data created successfully`);
  });
  connection.on('error', () => {
    console.error(`Error trying connect on MongoDB`);
  });
}
