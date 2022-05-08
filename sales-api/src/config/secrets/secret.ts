const env = process.env;

export const secret = {
  MONGO_DB_URL: env.MONGO_DB_URL ?? 'mongodb://root:root@localhost:27017/sales-db?authSource=admin',
  API_SECRET: env.API_SECRET ?? `YXV0aC1hcGkgc2VjcmV0IGRldiAxMjM0NTY=`,
  RABBITMQ_URL: env.RABBITMQ_URL ?? `amqp://${env.RABBITMQ_USER ?? 'root'}:${env.RABBITMQ_PASS ?? 'root'}@localhost:5672`,
  PRODUCT_SERVICE: `http://${env.PRODUCT_HOST ?? 'localhost'}:${env.PRODUCT_PORT ?? '8080'}/api/product-service`,
};


