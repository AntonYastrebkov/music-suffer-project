import React from 'react';
import ReactDOM from 'react-dom'; 
import { BrowserRouter as Router } from 'react-router-dom';
import { Provider } from 'react-redux';

import App from './components/app';
import ErrorBoundry from './components/error-boundry';
import store from './store';
import LibraryService from './services/LibraryService';
import AdminService from './services/AdminService';
import { LibraryServiceProvider, AdminServiceProvider } from './components/context';

const libraryService = new LibraryService();
const adminService = new AdminService();

ReactDOM.render(
  <Provider store={store}>
    <ErrorBoundry>
      <LibraryServiceProvider value={libraryService}>
        <AdminServiceProvider value={adminService}>
          <Router>
            <App />
          </Router>
        </AdminServiceProvider>
      </LibraryServiceProvider>
    </ErrorBoundry>
  </Provider>, 
  document.getElementById("root"));