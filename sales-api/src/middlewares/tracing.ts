import { NextFunction, Request, Response } from 'express';
import { HttpStatus } from '../config/constants/constants';
import { v4 as uuidv4 } from 'uuid';


export const validateTransactionId = (request: Request, response: Response, next: NextFunction) => {

  const { transactionid } = request.headers;
  if ( !transactionid ) {
    return response.status(HttpStatus.BAD_REQUEST).json({
      status: HttpStatus.BAD_REQUEST,
      message: `The transaction id header is required.`,
    });
  }

  const serviceid = uuidv4();

  console.info(`Received transactionid: ${transactionid} | serviceid generated: ${serviceid}`);

  request.headers['serviceid'] = serviceid;

  return next();
};


