const contentLoaded = (newContent) => {
  return {
    type: 'FETCH_CONTENT_SUCCESS',
    payload: newContent
  };
};

const contentRequested = () => {
  return {
    type: 'FETCH_CONTENT_REQUESTED'
  };
};

const contentFailed = (error) => {
  return {
    type: 'FETCH_CONTENT_FAILURE',
    payload: error
  };
};

const loginRequested = () => {
  return {
    type: 'FETCH_LOGIN_REQUESTED'
  };
};

const loginSuccess = (userData) => {
  return {
    type: 'FETCH_LOGIN_SUCCESS',
    payload: userData
  };
};

const loginFailed = (error) => {
  return {
    type: 'FETCH_LOGIN_FAILURE',
    payload: error
  };
};

const fetchAlbums = (libraryService) => (paging) => (dispatch) => {
  dispatch(contentRequested());
  libraryService.getAlbums(paging)
    .then((data) => dispatch(contentLoaded(data)))
    .catch((error) => dispatch(contentFailed(error)));
};

const fetchLogin = (authService) => (loginData) => (dispatch) => {
  dispatch(loginRequested());
  authService.login(loginData)
    .then((data) => dispatch(loginSuccess(data)))
    .catch((error) => dispatch(loginFailed(error)));
};

export {
  fetchAlbums,
  fetchLogin
};