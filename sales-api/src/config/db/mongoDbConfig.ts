import { secrets } from '../secrets/secrets';
import mongoose from 'mongoose';

export function connectInMongoDB() {
  mongoose.connect(secrets.MONGO_DB_URL);
  mongoose.connection.on('connected', () => {
    console.info(`The application connected to MongoDB successfully!`);
  });
  mongoose.connection.on('error', () => {
    console.error(`Error trying connect on MongoDB`);
  });
}
