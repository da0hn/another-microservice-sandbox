import User from '../../modules/user/model/User.js';
import bcrypt from 'bcrypt';


export async function createInitialData() {
  try {
    await User.sync({force: true});

    const password = await bcrypt.hash('123456', 10);

    await User.create({
      name: 'user test',
      email: 'testuser@gmail.com',
      password,
    });
  } catch (e) {
    console.error(`A error occurs: ${ e.message }`)
  }
}

