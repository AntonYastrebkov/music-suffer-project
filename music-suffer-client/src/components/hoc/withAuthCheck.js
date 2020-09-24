import React from 'react';
import { Redirect } from 'react-router-dom';
import { connect } from 'react-redux';

const withAuthCheck = () => (Wrapped) => {
  return (props) => {
    if (props.isAuthenticated) {
      return <Wrapped { ...props } />
    }

    return <Redirect to="/login" />
  }
};

export default withAuthCheck;