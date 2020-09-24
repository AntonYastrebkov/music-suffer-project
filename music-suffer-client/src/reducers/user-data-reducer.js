
const initialState = {
  isAuthenticated: false,
  loading: false,
  accessToken: '',
  refreshToken: '',
  userData: {},
  expiration: null
};

const updateAuthData = (state, action) => {
  if (state === undefined) {
    return initialState;
  }

  const { authData } = state;
  switch (action.type) {
    case 'FETCH_LOGIN_REQUESTED': 
      return {
        isAuthenticated: false,
        loading: true,
        accessToken: '',
        refreshToken: '',
        userData: {},
        expiration: null
      };
    case 'FETCH_LOGIN_SUCCESS':
      return {
        ...action.payload,
        loading: false,
        isAuthenticated: true
      };
    default:
      return { ...authData };
  };
};

export default updateAuthData;