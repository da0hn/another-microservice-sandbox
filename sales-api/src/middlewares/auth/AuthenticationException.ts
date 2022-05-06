export class AuthenticationException extends Error {
  constructor(public status: number, private message: string) {
    super(message);
    this.status = status;
    this.message = message;
    this.name = this.constructor.name;
    Error.captureStackTrace(this, this.constructor);
  }
}
