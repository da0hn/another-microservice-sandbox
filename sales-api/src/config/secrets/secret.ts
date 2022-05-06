const env = process.env;

export const secret = {
  MONGO_DB_URL: env.MONGO_DB_URL ?? 'mongodb://root:root@localhost:27017/sales-db?authSource=admin',
  API_SECRET: env.API_SECRET ?? `YXV0aC1hcGkgc2VjcmV0IGRldiAxMjM0NTY=`,
};


