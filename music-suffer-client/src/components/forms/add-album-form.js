import React, { Component } from 'react';
// import DropdownList from 'react-dropdown-input';

import { withAdminService } from '../hoc';

class AddAlbumForm extends Component {

  _songsId = 1;

  state = {
    name: '',
    year: '',
    genre: '',
    genres: [],
    artistList: [],
    artistName: '',
    image: null,
    compositions: [
      { id: 0, name: '', file: null }
    ]
  };

  componentDidMount() {
    let genres;
    this.props.adminService.getGenres()
      .then((data) => {
        genres = data.map((g) => (
          <option value={ g } key={ g }/>
        ));
        this.setState({ genres });
      });
  }

  onSubmit = (event) => {
    event.preventDefault();
    const { name, year, genre, image, compositions, artistList, artistName } = this.state;
    const artistId = artistList.find((a) => a.name === artistName).id;
    this.props.adminService.addAlbum(
      { name, year, genre, artistId },
      image,
      compositions
    );
    // TODO: process posiible errors...
    this._songsId = 1;
    this.setState({
      name: '',
      year: '',
      genre: '',
      artistName: '',
      artistList: [],
      image: null,
      compositions: [{ id: 0, name: '', file: null }]
    });
  };

  onNameChange = (event) => {
    this.setState({ 
      name: event.target.value 
    });
  };

  onGenreChange = (event) => {
    this.setState({ 
      genre: event.target.value 
    });
  };

  onYearChange = (event) => {
    this.setState({ 
      year: event.target.value 
    });
  };

  onArtistNameChange = (event) => {
    const artistName = event.target.value;
    this.props.adminService.getArtistList(artistName)
      .then((data) => {
        const artistList = data.map((a) => {
          return { name: a.name, id: a.id };
        });
        this.setState({
          artistName,
          artistList
        });
      });
    // TODO: add catch block
  };

  onCoverSelected = (event) => {
    this.setState({
      image: event.target.files[0]
    });
  };

  onCompositionNameChange = (event, id) => {
    event.preventDefault();
    this.setState((state) => {
      const { compositions } = state;
      const idx = compositions.findIndex((c) => c.id === id);
      return {
        ...state,
        compositions: [
          ...compositions.slice(0, idx),
          { id: id, name: event.target.value, file: compositions[idx].file },
          ...compositions.slice(idx + 1)
        ]
      };
    });
  };

  onAddComposition = (event) => {
    event.preventDefault();

    this.setState((state) => {
      const { compositions } = state;
      const id = this._songsId++;
      return {
        ...state,
        compositions: [
          ...compositions,
          { id: id, name: '', file: null }
        ]
      };
    });
  };

  onRemoveComposition = (event, id) => {
    event.preventDefault();
    this.setState((state) => {
      const { compositions } = state;
      const idx = compositions.findIndex((c) => c.id === id);
      return {
        ...state,
        compositions: [
          ...compositions.slice(0, idx),
          ...compositions.slice(idx + 1)
        ]
      };
    });
  }

  onMusicFileSelected = (event, id) => {
    event.preventDefault();
    this.setState((state) => {
      const { compositions } = state;
      const idx = compositions.findIndex((c) => c.id === id);
      return {
        ...state,
        compositions: [
          ...compositions.slice(0, idx),
          { id: id, name: compositions[idx].name, file: event.target.files[0] },
          ...compositions.slice(idx + 1)
        ]
      };
    });
  }

  render() {

    const compositions = this.state.compositions.map((c) => {
      return (
        <div className="form-row mb-2" key={c.id}>
          <div className="col-5">
            <input type="text"
                className="form-control mr-2"
                onChange={ (event) => {
                  event.persist();
                  this.onCompositionNameChange(event, c.id)
                } }
                placeholder="Name"
                value={ c.name }/>
          </div>
          <div className="col-5">
              <input type="file" 
                    className="form-control-file"
                    onChange={ (event) => {
                      event.persist();
                      this.onMusicFileSelected(event, c.id)
                    } } />
          </div>
          <button className="btn btn-outline-danger" name="add"
              onClick={ (event) => this.onRemoveComposition(event, c.id) }>
            <i className="fa fa-minus"/>
          </button>
        </div>
      );
    });

    const artists = this.state.artistList.map((a) => {
      return (
        <option value={ a.name } key={ a.id }/>
      );
    });

    return (
      <div align="center">
        <form className="add-album-form"
            onSubmit={ this.onSubmit }>
          <div className="row">
            <div className="col-5">
              <div className="form-group">
                <input type="text"
                    className="form-control"
                    onChange={ this.onNameChange }
                    placeholder="Name"
                    value={ this.state.name }/>
              </div>
              <div className="form-group">
                <input type="text" list="genres"
                    className="form-control"
                    onChange={ this.onGenreChange }
                    placeholder="Genre"
                    value={ this.state.genre }/>
                <datalist id="genres">
                  { this.state.genres }
                </datalist>
              </div>
              <div className="form-group">
                <input type="text"
                    className="form-control"
                    onChange={ this.onYearChange }
                    placeholder="Year"
                    value={ this.state.year }/>
              </div>
              <div className="form-group">
                <input type="text" id="artistDropdown" list="artists"
                    className="form-control"
                    onChange={ this.onArtistNameChange }
                    placeholder="Search artist..."
                    value={ this.state.artistName }/>
                <datalist id="artists">
                  { artists }
                </datalist>
              </div>
              <div className="form-group">
                <label htmlFor="albumCoverSelect">Select cover</label>
                <input type="file" 
                    className="form-control-file" 
                    id="albumCoverSelect"
                    onChange={ this.onCoverSelected } />
              </div>
            </div>

            <div className="col-7">
              { compositions }
              <div className="row justify-content-center">
                <button className="btn btn-outline-success"
                    onClick={ this.onAddComposition }>
                  <i className="fa fa-plus"/>
                </button>
              </div>
            </div>
          </div>
          
          <input type="submit" 
              value="Add"
              className="btn btn-lg btn-primary mt-3" />
        </form>
      </div>
    );
  }
}

export default withAdminService()(AddAlbumForm);