import { secret } from '../../config/secrets/secret';
import jwt from 'jsonwebtoken';
import { HttpStatus } from '../../config/constants/constants';
import { NextFunction, Request, Response } from 'express';
import { AuthenticatedUser } from './AuthenticatedUser';

const bearer = 'bearer';

type AuthenticationData = Request & {
  authUser: AuthenticatedUser
};

export const checkToken = async (request: Request, response: Response, next: NextFunction) => {
  try {
    const { authorization } = request.headers;

    if ( !authorization ) {
      const status = HttpStatus.FORBIDDEN;
      return response.status(status).json({
        status,
        message: 'Access token was not informed',
      });
    }

    const accessToken = authorization.toLowerCase().includes(bearer) ? authorization.split(' ')[1] : authorization;

    const decoded = jwt.verify(
      accessToken,
      secret.API_SECRET,
    ) as AuthenticationData;

    (request as AuthenticationData).authUser = decoded.authUser;

    return next();
  } catch (error: any) {

    return response.status(HttpStatus.INTERNAL_SERVER_ERROR).json({
      status: HttpStatus.INTERNAL_SERVER_ERROR,
      message: error.message,
    });

  }
};


