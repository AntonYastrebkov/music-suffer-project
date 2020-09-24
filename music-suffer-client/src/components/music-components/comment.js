import React, { Component } from 'react';

import { withAuthService } from '../hoc';

class Comment extends Component {

  render() {
    const { item, authService: { isAuthor, isAdmin } } = this.props;
    const { authorId } = item;
    const controls = isAdmin(authorId) || isAuthor(authorId) ?
      (
        <div className="d-flex flex-row-reverse">
          <button className="btn btn-outline-danger">
            <i className="fa fa-trash-o"/>
          </button>
          <button className="btn btn-outline-success">
            <i className="fa fa-edit" />
          </button>
        </div>
      ) : null;
    
    return (
      <div className="card mb-3">
        <div className="card-header d-flex justify-content-between align-items-center">
          <h4>{item.authorName}</h4>
          { controls }
        </div>
        <div className="card-body">
          <h6 className="card-subtitle text-muted">{item.createdAt}</h6>
          <div className="d-flex">
            <div className="p-2 flex-grow-1"><p className="lead"></p>{item.content}</div>
            <div className="p-2"><h5 className="display-4">{item.score}</h5></div>
          </div>
        </div>
      </div>
    )
  }
};

export default withAuthService()(Comment);