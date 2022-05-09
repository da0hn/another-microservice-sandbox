export class UserController {
  service;

  constructor(service) {
    this.service = service;
  }

  async findByEmail(request, response) {
    const { authUser } = request;
    const { email } = request.params;
    const userResponse = await this.service.findByEmail(email, authUser);
    return response.status(userResponse.status).json(userResponse);
  }


  async getAccessToken(request, response) {
    const { email, password } = request.body;

    const { transactionid, serviceid } = request.headers;

    console.info(`Request access token using: ${ JSON.stringify({
      email,
      password
    }) } | [ transactionId: ${ transactionid } | serviceId: ${ serviceid }]`);

    const userResponse = await this.service.getAccessToken(email, password);


    console.info(`Response from get access token: ${ JSON.stringify(userResponse) } | [ transactionId: ${ transactionid } | serviceId: ${ serviceid }]`);

    return response.status(200).json(userResponse);
  }

}


