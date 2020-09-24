import React from 'react';
import ReactDOM from 'react-dom'; 
import { BrowserRouter as Router } from 'react-router-dom';
import { Provider } from 'react-redux';

import App from './components/app';
import ErrorBoundry from './components/error-boundry';
import store from './store';
import LibraryService from './services/LibraryService';
import AdminService from './services/AdminService';
import AuthService from './services/AuthService';
import { 
  LibraryServiceProvider, 
  AdminServiceProvider, 
  AuthServiceProvider 
} from './components/context';

const libraryService = new LibraryService();
const adminService = new AdminService();
const authService = new AuthService();

ReactDOM.render(
  <Provider store={store}>
    <ErrorBoundry>
      <LibraryServiceProvider value={libraryService}>
        <AdminServiceProvider value={adminService}>
          <AuthServiceProvider value={authService}>
            <Router>
              <App />
            </Router>
          </AuthServiceProvider>
        </AdminServiceProvider>
      </LibraryServiceProvider>
    </ErrorBoundry>
  </Provider>, 
  document.getElementById("root"));