import React from 'react';

import ItemCard from '../item-card';

const ItemCardColumn = (props) => {

  const { onItemSelected, data } = props;

  const items = data.map((item) => {
    return (
      <div onClick={ onItemSelected } key={ item.id }>
        <ItemCard { ...item } />
      </div>
    );
  });

  return (
    <div className="card-columns">
      { items }
    </div>
  );
};

export default ItemCardColumn;