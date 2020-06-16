
import updatePagingStore from './paging-data-reducer';

const reducer = (state, action) => {
  return {
    pagingData: updatePagingStore(state, action)
  };
}

export default reducer;