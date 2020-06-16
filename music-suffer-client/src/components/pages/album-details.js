import React, { Component } from 'react';
import { Link } from 'react-router-dom';

import './pages.css';

import { CommentsList, MusicList } from '../music-components';
import { compose, withLibraryService, withData, withAuthService } from '../hoc';

class AlbumDetails extends Component {

  render() {
    const { data } = this.props;
    return (
      <div className="row no-glutters">
        <div className="col">
          <div className="card">
            <div className="row no-glutters">
              <div className="col-4 image">
                <img src="https://d1csarkz8obe9u.cloudfront.net/posterpreviews/music-logo-design-template-6140b09bce93da1f0244afc40640839f_screen.jpg?ts=1566982979"/>
              </div>
              <div className="col-8">
                <div className="card-body justify-content-start align-items-center">
                  <h2 className="card-title">{data.name}</h2>
                  <div className="row">
                    <div className="col-7">
                      <p className="card-text">by <Link to={`/artists/${data.artist.id}`}>
                          {data.artist.name}
                        </Link>
                      </p>
                      <p className="card-text">year: {data.year}</p>
                      <p className="card-text">genre: {data.genre}</p>
                    </div>
                    <div className="col-5 justify-content-end">
                      <p className="display-3">{data.averageScore}</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div>
            <MusicList compositions={ data.compositions } />
          </div>
        </div>

        <div className="col">
          <CommentsList id={data.id} />
        </div>
      </div>
    );
  }
};

const mapMethodsToProps = (libraryService) => {
  return {
    getData: libraryService.getAlbum
  };
};

export default compose(
  withAuthService(),
  withLibraryService(mapMethodsToProps),
  withData()
)(AlbumDetails);