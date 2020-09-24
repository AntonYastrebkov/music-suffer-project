import React, { Component } from 'react';

import './forms.css';

export default class LofinForm extends Component {

  state = {
    email: '',
    password: ''
  };

  onSubmit = (event) => {
    event.preventDefault();
    this.props.onLogin(this.state);
  }

  onEmailChange = (event) => {
    this.setState({
      email: event.target.value
    });
  };

  onPasswordChange = (event) => {
    this.setState({
      password: event.target.value
    });
  };

  render() {
    const { email, password } = this.state;

    return (
      <div className="login-form m-3">
        <form onSubmit={ this.onSubmit }>
          <div className="form-group">
            <input type="email"
                className="form-control"
                onChange={ this.onEmailChange }
                placeholder="email@example.com"
                value={ email } />
          </div>
          <div className="form-group">
            <input type="password"
                className="form-control"
                onChange={ this.onPasswordChange }
                placeholder="password"
                value={ password } />
          </div>
          <div className="row justify-content-center">
            <button className="btn btn-primary">
              sign in
            </button>
          </div>
        </form>
      </div>
    )
  }
}