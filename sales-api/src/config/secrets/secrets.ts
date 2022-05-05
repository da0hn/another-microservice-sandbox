const env = process.env;

export const secrets = {
  MONGO_DB_URL: env.MONGO_DB_URL ?? 'mongodb://localhost:27017/sales-db',
};


