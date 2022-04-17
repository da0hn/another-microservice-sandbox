import { Router } from 'express';
import { utils } from '../constants/route.js';
import { userRoute } from '../../modules/user/routes/UserRoutes.js';


export const router = Router();

router.use(`${ utils.baseUrl }/users`, userRoute);

router.get(`${ utils.baseUrl }/health/status`, (request, response) => {
  return response.json({
    service: 'auth-api',
    status: 'up',
    httpStatus: 200,
  });
});
