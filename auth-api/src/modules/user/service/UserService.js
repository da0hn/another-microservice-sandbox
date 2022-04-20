import { httpStatus } from '../../../config/constants/httpStatus.js';
import { UserException } from '../exceptions/UserException.js';
import bcrypt from 'bcrypt';
import jwt from 'jsonwebtoken';
import { apiSecret } from '../../../config/constants/secret.js';


export class UserService {
  repository;

  constructor(repository) {
    this.repository = repository;
  }

  async findByEmail(email, authUser) {
    try {

      this.validateRequestData(email);
      const maybeUser = await this.repository.findByEmail(email);
      this.validateUserNotFound(maybeUser);

      this.validateAuthenticatedUser(maybeUser, authUser);

      return {
        status: httpStatus.SUCCESS,
        user: {
          id: maybeUser.id,
          email: maybeUser.email,
          name: maybeUser.name,
        },
      };
    } catch (error) {
      return this.errorResponse(error);
    }
  }

  errorResponse(error) {
    return {
      status: error.status ?? httpStatus.INTERNAL_SERVER_ERROR,
      message: error.message,
    };
  }

  validateRequestData(email) {
    if (!email) {
      throw new UserException(
          httpStatus.BAD_REQUEST,
          `User email was not informed`,
      );
    }
  }

  validateUserNotFound(user) {
    if (!user) {
      throw new UserException(httpStatus.BAD_REQUEST, `User was not found`);
    }
  }

  async getAccessToken(email, password) {

    try {
      this.validateAccessTokenData(email, password);

      const user = await this.repository.findByEmail(email);

      this.validateUserNotFound(user);

      await this.validatePassword(password, user);

      const authenticatedUser = { id: user.id, email: user.email, name: user.name };

      const accessToken = jwt.sign(
          { authUser: authenticatedUser },
          apiSecret,
          { expiresIn: '1d' },
      );

      return {
        status: httpStatus.SUCCESS,
        accessToken,
      };
    } catch (error) {
      return this.errorResponse(error);
    }
  }

  validateAccessTokenData(email, password) {
    if (!email || !password) {
      throw new UserException(
          httpStatus.UNAUTHORIZED,
          `Email and password must be informed.`,
      );
    }
  }


  validateAuthenticatedUser(user, authUser) {
    if (!authUser || user.id !== authUser.id) {
      throw new UserException(httpStatus.FORBIDDEN, `You cannot see this user data`);
    }
  }

  async validatePassword(password, user) {
    if (!await bcrypt.compare(password, user.password)) {
      throw new UserException(httpStatus.UNAUTHORIZED, `Password doesn't match`);
    }
  }
}

