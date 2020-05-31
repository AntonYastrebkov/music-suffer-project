import React from 'react';
import ReactDOM from 'react-dom'; 
import { BrowserRouter as Router } from 'react-router-dom';
import { Provider } from 'react-redux';

import App from './components/app';
import ErrorBoundry from './components/error-boundry';
import store from './store';
import LibraryService from './services/LibraryService';
import { LibraryServiceProvider } from './components/library-service-context';

const libraryService = new LibraryService();

ReactDOM.render(
  <Provider store={store}>
    <ErrorBoundry>
      <LibraryServiceProvider value={libraryService}>
        <Router>
          <App />
        </Router>
      </LibraryServiceProvider>
    </ErrorBoundry>
  </Provider>, 
  document.getElementById("root"));