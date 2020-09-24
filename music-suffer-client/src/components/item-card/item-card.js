import React from 'react';

const ItemCard = (props) => {
  const {
    name,
    coverPath,
    year,
    genre
  } = props;
  return (
    <div className="card my-3">
      <img src={coverPath} alt="smth" className="card-img-top rounded" />
      <div className="m-2">
            <span className="lead">{ name }</span>
        </div>
        <div className="card-footer text-muted m-1">
            <p>{ year }</p>
        </div>
        <div className="card-footer text-muted text-right">
            <a href="/#" className="btn btn-primary">Info</a>
        </div>
    </div>
  );
};

export default ItemCard;