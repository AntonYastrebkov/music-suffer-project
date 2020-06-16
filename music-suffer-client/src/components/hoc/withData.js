import React, { Component } from 'react';

import ErrorIndicator from '../error-indicator';
import Spinner from '../spinner';

const withData = () => (View) => {
  return class extends Component {

    state = {
      data: null,
      loading: true,
      hasError: false
    };

    update() {
      this.setState({
        hasError: false,
        loading: true
      });
      this.props.getData(this.props.id)
        .then((data) => {
          this.setState({
            data,
            loading: false
          });
        })
        .catch(() => {
          this.setState({
            hasError: true,
            loading: false
          });
        });
    }

    componentDidMount() {
      this.update();
    }

    componentDidUpdate(prevProps) {
      if (prevProps.id !== this.props.id) {
        this.update();
      }
    }

    render() {
      const { loading, hasError, data } = this.state;
      if (hasError) {
        return <ErrorIndicator />;
      }
      if (loading) {
        return <Spinner />;
      }

      return <View { ...this.props } data={ data } />;
    }
  };
};

export default withData;