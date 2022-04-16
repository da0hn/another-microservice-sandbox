export class UserController {
  service;

  constructor(service) {
    this.service = service;
  }

  async findByEmail(request, response) {
    const {email} = request.params;
    const userResponse = await this.service.findByEmail(email);
    return response.status(userResponse.status).json(userResponse);
  }

}


