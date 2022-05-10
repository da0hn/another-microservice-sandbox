import OrderDocument from '../../modules/sales/repositories/OrderSchema';
import { v4 as uuid } from 'uuid';
import { Status } from '../../modules/sales/model/Order';

export async function createInitialData() {
  await OrderDocument.collection.drop();

  await OrderDocument.create({
    products: [
      {
        productId: 1,
        quantity: 3,
      },
      {
        productId: 2,
        quantity: 1,
      },
      {
        productId: 3,
        quantity: 2,
      },
    ],
    user: {
      id: uuid().toString(),
      name: 'user test 1',
      email: 'usertest1@gmail.com',
    },
    status: Status.APPROVED,
    createdAt: new Date(),
    updatedAt: new Date(),
    transactionId: uuid(),
    serviceId: uuid(),
  });
  await OrderDocument.create({
    products: [
      {
        productId: 1,
        quantity: 9,
      },
      {
        productId: 2,
        quantity: 10,
      },
    ],
    user: {
      id: uuid().toString(),
      name: 'user test 2',
      email: 'usertest2@gmail.com',
    },
    status: Status.REJECTED,
    createdAt: new Date(),
    updatedAt: new Date(),
    transactionId: uuid(),
    serviceId: uuid(),
  });

  const order = await OrderDocument.find();
  console.info(`Initial data was created: \n${JSON.stringify(order, null, 2)}`);
}










