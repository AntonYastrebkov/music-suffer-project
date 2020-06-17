import React, { Component } from 'react';
import { bindActionCreators } from 'redux';
import { connect } from 'react-redux';
import { Redirect, Link, withRouter } from 'react-router-dom';

import { LoginForm, SignupForm } from '../forms';
import Spinner from '../spinner';
import { compose, withAuthService } from '../hoc';
import { fetchLogin } from '../../actions';

class LoginPage extends Component {

  state = {
    register: false,
    registered: false,
    error: false
  };

  onLogin = (loginData) => {
    this.props.fetchLogin(loginData);
  }

  onSignup = (data) => {
    this.setState({ 
      register: true,
      registered: false,
      error: false  
    });
    this.props.fetchSignup(data)
      .then((data) => {
        this.setState({ register: true });
        setTimeout(() => {
          this.setState({
            register: false,
            registered: false,
            error: false
          });
          this.props.history.push('/login');
        }, 5000);
      })
      .catch((err) => {
        this.setState({ error: true });
      })
  }

  render() {
    const { loading, isAuthenticated, signup } = this.props;
    if (isAuthenticated) {
      return <Redirect to="/" />;
    }

    const { register, registered, error } = this.state;
    if (loading || register) {
      return (
        <div className="justify-content-center">
          <Spinner />
        </div>
      )
    }
    if (registered) {
      
    }

    const form = signup === undefined ? (
      <React.Fragment>
        <LoginForm onLogin={ this.onLogin }/>
        <div className="card-footer text-center">
          <h6 className="text-muted">
            or <Link to="/signup/">signup</Link>
          </h6>
        </div>
      </React.Fragment>
    ) : (
      <div>
        <SignupForm onSignup={ this.onSignup }/>
      </div>
    );

    return (
      <div className="row justify-content-center mt-4 mb-5">
        <div className="card">
          { form }
        </div>
      </div>
    )
  }
};

const mapStateToProps = ({ authData }) => {
  return { ...authData };
};

const mapDispathToProps = (dispatch, { authService }) => {
  return bindActionCreators({
    fetchLogin: fetchLogin(authService)
  }, dispatch);
};

export default compose(
  withRouter,
  withAuthService(),
  connect(mapStateToProps, mapDispathToProps),
)(LoginPage);