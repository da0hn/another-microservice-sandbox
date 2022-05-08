export const constants = {
  BASE_URL: '/api/sales-service',
};
export const HttpStatus = {
  INTERNAL_SERVER_ERROR: 500,
  SUCCESS: 200,
  UNAUTHORIZED: 401,
  FORBIDDEN: 403,
  BAD_REQUEST: 400,
  NOT_FOUND: 404,
  SERVICE_UNAVAILABLE: 503,
};
export const RabbitMQ = {
  PRODUCT_TOPIC: 'product.topic',
  PRODUCT_STOCK_UPDATE_QUEUE: 'product-stock-update.queue',
  PRODUCT_STOCK_UPDATE_ROUTING_KEY: 'product-stock-update.routing-key',
  SALES_CONFIRMATION_QUEUE: 'sales-confirmation.queue',
  SALES_CONFIRMATION_ROUTING_KEY: 'sales-confirmation.routing-key',
};

