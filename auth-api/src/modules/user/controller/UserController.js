export class UserController {
  service;

  constructor(service) {
    this.service = service;
  }

  async findByEmail(request, response) {
    const userResponse = await this.service.findByEmail(request);
    return response.status(userResponse.status).json(userResponse);
  }

}


