import jwt from 'jwt-decode';

export default class AuthService {
  _apiBasePath = 'http://localhost:8080/api/user';

  _postRequest = async (path, body, params = '') => {
    const result = await fetch(`${this._apiBasePath}${path}`, {
      method: 'POST',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json'
      },
      body: body
    });

    return result;
  }

  isAuthenticated = () => {
    return false;
  };

  isAdmin = () => {
    return true;
  };

  isAuthor = (id) => {
    return true;
  };

  login = async ({ email, password }) => {
    const body = JSON.stringify({
      'email': email,
      'password': password
    });
    const result = await this._postRequest('/login', body);
    const proccessedResult = result.json()
      .then((data) => {
        return this._decodeAuthentication(data);
      })
    return proccessedResult;
  };

  _decodeAuthentication = (auth) => {
    const {accessToken, refreshToken, expiration } = auth;
    const userData = jwt(accessToken);
    return {
      accessToken,
      refreshToken,
      userData,
      expiration: new Date(expiration * 1000)
    };
  };

  register = (userData) => {
    const body = JSON.stringify(userData);
    const result = this._postRequest('/register', body);
    return result;
  };
};