import User from '../model/User.js';


export class UserRepository {

  async findById(userId) {
    try {
      return await User.findOne({where: {id: userId}});
    } catch (error) {
      console.error(`ERROR: ${ error.message }`);
      return null;
    }
  }

  async findByEmail(email) {
    try {
      return await User.findOne({where: {email: email}});
    } catch (error) {
      console.error(`ERROR: ${ error.message }`);
      return null;
    }
  }

}


