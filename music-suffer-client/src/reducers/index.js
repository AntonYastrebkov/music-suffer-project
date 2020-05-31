
const initialState = {
  albums: [],
  artists: []
};

const reducer = (state, action) => {
  if (state === undefined) {
    return initialState;
  }

  switch(action.type) {
    case 'TEST':
      return state;
    default:
      return state;
  }
}

export default reducer;