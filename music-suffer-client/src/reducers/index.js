
import updatePagingStore from './paging-data-reducer';
import updateAuthData from './user-data-reducer';

const reducer = (state, action) => {
  return {
    pagingData: updatePagingStore(state, action),
    authData: updateAuthData(state, action)
  };
};

export default reducer;