import { Router } from 'express';
import { constants } from '../constants/constants';
import { orderRoute } from '../../modules/sales/controllers/OrderRoute';

export const router = Router();

router.use(`${constants.BASE_URL}/orders`, orderRoute);
