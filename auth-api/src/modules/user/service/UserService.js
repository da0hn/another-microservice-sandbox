import { httpStatus } from '../../../config/constants/httpStatus.js';


export class UserService {
  repository;

  constructor(repository) {
    this.repository = repository;
  }

  async findByEmail(req) {
    try {

      const {email} = req.params;
      this.validateRequestData(email);
      const maybeUser = await this.repository.findByEmail(email);
      this.validateUserNotFound(maybeUser);

      return {
        status: httpStatus.SUCCESS,
        user: {
          ...maybeUser,
        },
      }
    } catch (error) {
      return {
        status: error.status ?? httpStatus.INTERNAL_SERVER_ERROR,
        message: error.message,
      }
    }
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
    if (!user)
      throw new UserException(httpStatus.BAD_REQUEST, `User was not found`);
  }
}

