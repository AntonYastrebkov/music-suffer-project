import React, { Component } from 'react';

import Paging from '../paging';
import ErrorIndicator from '../error-indicator';
import Spinner from '../spinner';

const withPagingData = () => (View) => {
  return class extends Component {

    update(pageNumber) {
      const { fetchData, paging } = this.props;
      console.log('TRY TO FETCH', paging);
      console.log('ALL PROPS', this.props);

      fetchData({
        ...paging,
        page: pageNumber
      });
    }

    componentDidMount() {
      this.update(0);
    }

    render() {
      const { loading, hasError, content, paging } = this.props;
      
      let pages = paging.totalPages !== 0 ? <Paging paging={ paging } 
          onPageClick={(pageNumber) => this.update(pageNumber)}/> : null;
      if (hasError) {
        return (
          <React.Fragment>
            <ErrorIndicator />
            { pages }
          </React.Fragment>
        );
      }
      if (loading) {
        return (
          <React.Fragment>
            <Spinner />
            { pages }
          </React.Fragment>
        );
      }

      return (
        <div>
          <View { ...this.props } data={ content } /> 
          { pages } 
        </div>
      );
    }
  };
};

export default withPagingData;