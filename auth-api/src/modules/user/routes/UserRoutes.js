import { Router } from 'express';
import { UserService } from '../service/UserService.js';
import { UserRepository } from '../repository/UserRepository.js';
import { UserController } from '../controller/UserController.js';
import checkToken from '../../../middlewares/auth/checkToken.js';


const userRepository = new UserRepository();
const userService = new UserService(userRepository);
const userController = new UserController(userService);


const userRoute = new Router();

userRoute.get(`/:email`, checkToken, (request, response) => userController.findByEmail(request, response));

userRoute.post(`/access-token`, (request, response) => userController.getAccessToken(request, response));

export { userRoute };
