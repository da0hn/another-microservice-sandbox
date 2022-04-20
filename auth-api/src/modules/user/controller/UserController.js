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
    const userResponse = await this.service.getAccessToken(email, password);
    return response.status(200).json(userResponse);
  }

}


