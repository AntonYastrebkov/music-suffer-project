import React from 'react';

const ItemList = (props) => {
  const { data } = props;

  const items = data.map((item) => {
    return (
      React.Children.map(props.children, (child) => {
        return React.cloneElement(child, { item });
      })
    )
  });

  return <h1>AAAAAAAAAAAAA</h1>;
};