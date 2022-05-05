const env = process.env;

export const constants = {
  BASE_URL: '/api/sales-service',
  MONGO_DB_URL: env.MONGO_DB_URL ?? 'mongodb://localhost:27017/sales-db',
};


