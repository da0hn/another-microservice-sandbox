import { Router } from 'express';
import { constants } from '../constants/constants';
import { orderRoute, productRoute } from '../../modules/sales/controllers/OrderRoute';

export const router = Router();

router.use(`${constants.BASE_URL}/orders`, orderRoute);
router.use(`${constants.BASE_URL}/products`, productRoute);
