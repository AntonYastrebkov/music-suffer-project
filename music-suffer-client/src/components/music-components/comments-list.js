import React from 'react';

import Comment from './comment';
import {
  compose,
  withPagingData,
  withLibraryService
} from '../hoc';

const CommentsList = (props) => {
  const { data } = props;
  
  const comments = data.map((item) => {
    return (
      <div key={item.id}>
        <Comment item={item} />
      </div>
    );
  });

  return (
    <div>
      { comments }
    </div>
  );
};

const mapCommentsMethodToProps = (libraryService) => {
  return {
    getData: libraryService.getComments
  };
};

export default compose(
  withLibraryService(mapCommentsMethodToProps),
  withPagingData()
)(CommentsList);