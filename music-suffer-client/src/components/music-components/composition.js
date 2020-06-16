import React from 'react';

const Composition = ({composition}) => {
  return (
    <li className="list-group-item"
        key={composition.id}>
      <div className="row">
        <div className="col-1">
          { composition.position }
        </div>
        <div className="col-11">
          { composition.name }
        </div>
      </div>
    </li>
  );
};

export default Composition;