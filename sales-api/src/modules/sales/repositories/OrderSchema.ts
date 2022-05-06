import { model, Schema } from 'mongoose';
import { Order } from '../model/Order';


const OrderDocument = model('Order', new Schema<Order>({
  products: [ { productId: Number, quantity: Number } ],
  user: {
    type: Object,
    required: true,
  },
  status: {
    type: String,
    required: true,
  },
  createdAt: {
    type: Date,
    required: true,
  },
  updatedAt: {
    type: Date,
    required: true,
  },
}));

export default OrderDocument;

