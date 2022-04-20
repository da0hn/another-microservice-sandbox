import User from '../../modules/user/model/User.js';
import bcrypt from 'bcrypt';


export async function createInitialData() {
  try {
    await User.sync({ force: true });

    const password = await bcrypt.hash('123456', 10);

    await User.create({
      name: 'user test 1',
      email: 'testuser1@gmail.com',
      password,
    });

    await User.create({
      name: 'user test 2',
      email: 'testuser2@gmail.com',
      password,
    });

    await User.create({
      name: 'user test 3',
      email: 'testuser3@gmail.com',
      password,
    });

  } catch (error) {
    console.error(`A error occurs: ${ error.message }`);
  }
}

