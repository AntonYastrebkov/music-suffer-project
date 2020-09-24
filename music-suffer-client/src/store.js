import { createStore, applyMiddleware } from 'redux';
import thunkMiddleware from 'redux-thunk';

import reducer from './reducers';

const store = createStore(reducer, applyMiddleware(thunkMiddleware));
store.dispatch({type: 'TEST'});

export default store;