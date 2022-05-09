import { httpStatus } from './constants/httpStatus.js';
import { v4 as uuidv4 } from 'uuid';


export const validateTransactionId = (request, response, next) => {

  const { transactionid } = request.headers;

  if (!transactionid) {
    return response.status(httpStatus.BAD_REQUEST).json({
      status: httpStatus.BAD_REQUEST,
      message: `The transaction id header is required.`,
    });
  }
  request.headers.serviceid = uuidv4();

  return next();
};


