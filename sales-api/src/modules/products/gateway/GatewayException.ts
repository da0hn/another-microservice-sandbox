import { HttpStatus } from '../../../config/constants/constants';

export class GatewayException extends Error {
  constructor(public message: string, public httpStatus: number = HttpStatus.SERVICE_UNAVAILABLE) {
    super(message);
  }
}

