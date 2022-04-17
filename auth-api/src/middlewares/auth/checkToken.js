import { AuthenticationException } from './AuthenticationException.js';
import { httpStatus } from '../../config/constants/httpStatus.js';
import { apiSecret } from '../../config/constants/secret.js';
import jwt from 'jsonwebtoken';
import { promisify } from 'util';


const bearer = 'bearer';

export default async (request, response, next) => {
  try {
    const { authorization } = request.headers;

    if (!authorization) {
      throw new AuthenticationException(httpStatus.FORBIDDEN, `Access token was not informed`);
    }

    const accessToken = authorization.toLowerCase().includes(bearer) ? authorization.split(' ')[1] : authorization;

    const decoded = await promisify(jwt.verify)(
        accessToken,
        apiSecret
    );

    request.authUser = decoded.authUser;
    return next();

  } catch (error) {
    const status = error.status ?? httpStatus.INTERNAL_SERVER_ERROR;
    return response.status(status).json({
      status,
      message: error.message
    });
  }
}


