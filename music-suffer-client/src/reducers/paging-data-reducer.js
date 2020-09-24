const initialState = {
  content: [],
  paging: {
    page: 0,
    totalPages: 0,
    size: 12,
    sort: 'id',
    direction: 'desc',
    first: true,
    last: false
  },
  hasError: false,
  loading: true
};

const updatePageStore = (state, action) => {
  if (state === undefined) {
    return initialState;
  }

  const pagingData = state.pagingData;
  switch(action.type) {
    case 'FETCH_CONTENT_REQUESTED':
      return {
        ...pagingData,
        content: [],
        loading: true,
        hasError: false
      };
    case 'FETCH_CONTENT_FAILURE':
      return {
        ...pagingData,
        content: [],
        loading: false,
        hasError: true
      };
    case 'FETCH_CONTENT_SUCCESS':
      const { paging, content } = action.payload;
      return {
        content,
        paging,
        loading: false,
        hasError: false
      }
    default:
      return pagingData;
  }
};

export default updatePageStore;