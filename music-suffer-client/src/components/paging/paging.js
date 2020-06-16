import React, { Component } from 'react';

export default class Paging extends Component {

  state = {
    buttons: []
  };

  constructor(props) {
    super(props);
    const { totalPages, page } = props.paging;
    if (totalPages > 7) {
      const current = page + 1;
      const head = current > 4 ? [1, -1] : [1, 2, 3];
      const tail = current < totalPages - 3 ? [-1, totalPages] : [totalPages - 2, totalPages - 1, totalPages];
      const bodyBefore = current > 4 && current < totalPages - 1 ? [current - 2, current - 1] : [];
      const bodyAfter = current > 2 && current < totalPages - 3 ? [current + 1, current + 2] : [];
      const center = current > 3 && current < totalPages - 2 ? [current] : [];
      const body = head.concat(bodyBefore, center, bodyAfter, tail);
      this.state = {
        buttons: body
      };
    } else {
      this.state = {
        buttons: Array.apply(null, new Array(totalPages)).map((_, idx) => idx + 1)
      };
    }
  }

  render() {
    const { paging: {page}, onPageClick } = this.props;

    const elements = this.state.buttons.map((b, idx) => {
      if (b === -1) {
        return (
          <button key={idx} className="btn btn-outline-secondary" disabled>
            ...
          </button>
        );
      } else if (b === page + 1) {
        return (
          <button key={idx} className="btn btn-primary">
            { b }
          </button>
        );
      } else {
        return (
          <button key={idx} className="btn btn-outline-primary"
              onClick={() => onPageClick(b - 1)}>
            { b }
          </button>
        );
      }
    })
    return (
      <div className="pagination justify-content-center">
        { elements }
      </div>
    );
  }
};