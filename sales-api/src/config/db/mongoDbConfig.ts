import { constants } from '../secrets/constants';
import mongoose from 'mongoose';

export function connectInMongoDB() {
  mongoose.connect(constants.MONGO_DB_URL);
  mongoose.connection.on('connected', () => {
    console.info(`The application connected to MongoDB successfully!`);
  });
  mongoose.connection.on('error', () => {
    console.error(`Error trying connect on MongoDB`);
  });
}
