export class OrderException extends Error {

  constructor(public httpStatus: number, public message: string) {
    super(message);
    this.httpStatus = httpStatus;
  }
}
