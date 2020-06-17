import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';

import Spinner from '../spinner';
import { SignupForm } from '../forms';
import { compose, withAuthService } from '../hoc';

class SignupPage extends Component {

  state = {
    loading: false,
    success: false,
    error: false
  };

  onSignup = (data) => {
    this.setState({ 
      loading: true,
      error: false
    });
    this.props.authService.register(data)
      .then(() => {
        this.setState({
          loading: false,
          success: true
        });
        setTimeout(() => {
          this.props.history.push('/login');
        }, 3000);
      })
      .catch(() => {
        this.setState({
          loading: false,
          error: true
        });
      });
  }

  render() {
    const { loading, error, success } = this.state;
    if (loading) {
      return (
        <div className="justify-content-center">
          <Spinner />
        </div>
      );
    }
    if (success) {
      return (
        <div className="container">
          <div className="d-flex justify-content-center">
            <p className="display-4 align-middle">You were registered!</p>
          </div>
          <div className="d-flex justify-content-center">
            <h5>Redirecting to login page...</h5>
          </div>
        </div>
      );
    }

    const errorMessage = error ? (
      <div className="card-footer text-center">
        <p className="text-danger">Something went wrong</p>
      </div>
    ) : null;

    return (
      <div className="row justify-content-center mt-4 mb-5">
        <div className="card">
          <SignupForm onSignup={ this.onSignup }/>
          { errorMessage }
        </div>
      </div>
    );
  }
};

export default compose(
  withRouter,
  withAuthService()
)(SignupPage);