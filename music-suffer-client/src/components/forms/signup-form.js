import React, { Component } from 'react';

import './forms.css';

export default class LofinForm extends Component {

  state = {
    email: '',
    password: '',
    confirmPassword: '',
    firstName: '',
    lastName: '',
  };

  onSubmit = (event) => {
    event.preventDefault();
    const userData = {...this.state};
    delete userData.confirmPassword;
    this.props.onSignup(userData);
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

  onConfirmPasswordChange = (event) => {
    this.setState({
      confirmPassword: event.target.value
    });
  };

  onFirstNameChange = (event) => {
    this.setState({
      firstName: event.target.value
    });
  };

  onLastNameChange = (event) => {
    this.setState({
      lastName: event.target.value
    });
  };

  render() {
    const { 
      email, 
      password,
      confirmPassword,
      firstName,
      lastName,
    } = this.state;

    const passwordWarn = password !== confirmPassword ? (
      <small id="passwordHelp" className="form-text text-muted">Passwords should be equals</small>
    ) : null;

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
            <input type="text"
                className="form-control"
                onChange={ this.onFirstNameChange }
                placeholder="first name"
                value={ firstName } />
          </div>
          <div className="form-group">
            <input type="text"
                className="form-control"
                onChange={ this.onLastNameChange }
                placeholder="last name"
                value={ lastName } />
          </div>
          <div className="form-group">
            <input type="password"
                className="form-control"
                onChange={ this.onPasswordChange }
                placeholder="password"
                value={ password } />
          </div>
          <div className="form-group">
            <input type="password"
                aria-describedby="passwordHelp"
                className="form-control"
                onChange={ this.onConfirmPasswordChange }
                placeholder="confirm"
                value={ confirmPassword } />
            { passwordWarn }
          </div>
          <div className="row justify-content-center">
            <button className="btn btn-primary">
              sign up
            </button>
          </div>
        </form>
      </div>
    )
  }
}