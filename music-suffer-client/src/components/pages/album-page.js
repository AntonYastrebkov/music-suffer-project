import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import { ItemCardColumn } from '../lists';
import { fetchAlbums } from '../../actions';
import { 
  compose, 
  withPagingData,
  withLibraryService,
  withAuthCheck
} from '../hoc';

const mapAlbumMethodsToProps = (libraryService) => {
  return { getData: libraryService.getAlbums };
}

const mapStateToProps = ({ pagingData, authData: {isAuthenticated} }) => {
  return { ...pagingData, isAuthenticated };
}

const mapDispatchToProps = (dispatch, { libraryService }) => {
  return bindActionCreators({
    fetchData: fetchAlbums(libraryService)
  }, dispatch);
};

const AlbumPage = (props) => {
  const List = compose(
    withLibraryService(mapAlbumMethodsToProps),
    connect(mapStateToProps, mapDispatchToProps),
    withAuthCheck(),
    withPagingData()
  )(ItemCardColumn);
  return (
    <div className="mb-3">
      <List listType="card-column"/>
    </div>
  );
};

export default (AlbumPage);