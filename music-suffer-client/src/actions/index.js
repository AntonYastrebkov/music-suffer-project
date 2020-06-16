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

const fetchAlbums = (libraryService) => (paging) => (dispatch) => {
  dispatch(contentRequested());
  libraryService.getAlbums(paging)
    .then((data) => dispatch(contentLoaded(data)))
    .catch((error) => dispatch(contentFailed(error)));
};

export {
  fetchAlbums
};