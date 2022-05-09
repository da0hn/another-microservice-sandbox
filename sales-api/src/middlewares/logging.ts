import { NextFunction, Request, Response } from 'express';


export const logging = (request: Request, response: Response, next: NextFunction) => {

  const { method, baseUrl } = request;

  const { transactionid, serviceid } = request.headers;

  if ( method === `POST` ) {
    const { body } = request;

    console.info(`${method} in ${baseUrl} request using body ${JSON.stringify({ body })} | [ transactionId: ${transactionid} | serviceId: ${serviceid} ]`);

    return next();
  }

  if ( method === `GET` ) {
    const parameters = { ...request.params };
    console.info(`${method} in url ${baseUrl} request using parameter ${JSON.stringify(parameters)} | [ transactionId: ${transactionid} | serviceid: ${serviceid} ]`);
    return next();
  }

  if ( method === `PUT` ) {
    const parameters = { ...request.params };
    const { body } = request;
    console.info(`${method} in url ${baseUrl} request using parameter ${JSON.stringify(parameters)} and body ${JSON.stringify(body)} | [ transactionId: ${transactionid} | serviceid: ${serviceid} ]`);
    return next();
  }

  return next();
};
