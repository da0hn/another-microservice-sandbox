import { ProductGateway } from './ProductGateway';
import axios from 'axios';
import { secret } from '../../../config/secrets/secret';


const httpClient = axios;


const productUrl = secret.PRODUCT_SERVICE;

export const productGateway = new ProductGateway(httpClient, productUrl);


