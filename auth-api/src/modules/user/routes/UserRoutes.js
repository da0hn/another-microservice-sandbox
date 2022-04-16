import { Router } from 'express'
import { UserService } from '../service/UserService.js';
import { UserRepository } from '../repository/UserRepository.js';
import { UserController } from '../controller/UserController.js';


const userRepository = new UserRepository();
const userService = new UserService(userRepository);
const userController = new UserController(userService);


const userRoute = new Router();

userRoute.get(`/:email`, (request, response) => userController.findByEmail(request, response));

export { userRoute };
