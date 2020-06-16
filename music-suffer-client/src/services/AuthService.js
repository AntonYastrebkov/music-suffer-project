export default class AuthService {

  isAuthenticated = () => {
    return false;
  };

  isAdmin = () => {
    return true;
  };

  isAuthor = (id) => {
    return true;
  }
};