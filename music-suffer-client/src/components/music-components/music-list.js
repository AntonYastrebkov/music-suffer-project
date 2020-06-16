import React from 'react';

import Composition from './composition';

const MusicList = (props) => {
  const { compositions } = props;
  const elements = compositions.map((c) => {
    return <Composition composition={ c } />;
  });
  return (
    <ul className="list-group mt-3">
      { elements }
    </ul>
  );
};

export default MusicList;