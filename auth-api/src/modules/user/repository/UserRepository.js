import User from '../model/User.js';


export default class UserRepository {

  async findById(id) {
    try {
      return await User.findOne({where: id});
    } catch (error) {
      console.error(error.message);
      return null;
    }
  }

  async findByEmail(email) {
    try {
      return await User.findOne({where: email});
    } catch (error) {
      console.error(error.message);
      return null;
    }
  }

}


