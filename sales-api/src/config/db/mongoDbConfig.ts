import { constants } from '../secrets/constants';
import { connect, connection } from 'mongoose';

export function connectInMongoDB() {
  connect(constants.MONGO_DB_URL);
  connection.on('connected', () => {
    console.info(`The application connected to MongoDB successfully!`);
  });
  connection.on('error', () => {
    console.error(`Error trying connect on MongoDB`);
  });
}
