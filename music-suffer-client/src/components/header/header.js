import React from 'react';
import { Link, withRouter } from 'react-router-dom';

import './header.css';

import { compose, withAuthService } from '../hoc';

const Header = ({ history, authService }) => {
  const adminLinks = authService.isAdmin() ? (
    <div className="d-flex">
      <li>
        <Link to="/admin/management/">Music management</Link>
      </li>
      <li>
        <Link to="/admin/users/">User management</Link>
      </li>
    </div>
  ) : null;

  const account = authService.isAuthenticated() ? (
    <div>
      <button className="btn fas fa-user-alt"
          onClick={() => history.push('/profile')} />
    </div>
  ) : (
    <button className="btn btn-sm btn-primary"
        onClick={() => history.push('/login')}>
      Login
    </button>
  );
  return (
    <div className="header d-flex">
      <h3><Link to="/">Music Project</Link></h3>
      <ul className="d-flex">
        <li>
          <Link to="/albums/">Albums</Link>
        </li>
        <li>
          <Link to="/artists/">Artists</Link>
        </li>
        <li>
          <Link to="/genres/">Genres</Link>
        </li>
        { adminLinks }
      </ul>
      <div>
        { account }
      </div>
    </div>
  );
};

export default compose(
  withRouter,
  withAuthService()
)(Header);