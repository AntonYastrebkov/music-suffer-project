import React, { Component } from 'react';

import './forms.css';

import { withAdminService } from '../hoc';

class AddArtistForm extends Component {

  state = {
    name: '',
    biography: '',
    country: '',
    isGroup: false,
    image: null,
    error: false
  };

  onSubmit = (event) => {
    event.preventDefault();

    const { name, biography, country, isGroup, image } = this.state;
    this.props.adminService.addArtist({
      name, biography, country, isGroup
    }, image)
      .then((_) => {
        this.setState({
          name: '',
          biography: '',
          country: '',
          isGroup: false,
          error: false
        });
      })
      .catch((err) => {
        this.setState((state) => {
          return {
            ...state,
            error: true
          };
        });
      });
  };

  onNameChange = (event) => {
    this.setState({ 
      name: event.target.value 
    });
  };

  onCountryChange = (event) => {
    this.setState({ 
      country: event.target.value 
    });
  };

  onBiographyChange = (event) => {
    this.setState({ 
      biography: event.target.value 
    });
  };

  onGroupChange = (event) => {
    this.setState({ 
      isGroup: event.target.checked 
    });
  };

  onFileSelected = (event) => {
    this.setState({
      image: event.target.files[0]
    });
  };

  render() {
    const message = this.state.error ? (<p>Something went wrong</p>) : null;
    return (
      <div className="container justify-content-center" align="center">

        <form className="add-album-form justify-content-center"
            onSubmit={ this.onSubmit }>
          <div className="form-group">
            <input type="text"
                className="form-control"
                onChange={ this.onNameChange }
                placeholder="Name"
                value={ this.state.name }/>
          </div>
          <div className="form-group">
            <input type="text"
                className="form-control"
                onChange={ this.onCountryChange }
                placeholder="Country"
                value={ this.state.country }/>
          </div>
          <div className="form-group">
            <textarea rows="3"
                className="form-control"
                onChange={ this.onBiographyChange }
                placeholder="Biography"
                value={ this.state.biography }></textarea>
          </div>
          <div className="form-group form-check">
            <input type="checkbox" 
                id="isGroupCheckbox"
                className="form-check-input" 
                onChange={ this.onGroupChange }/>
            <label className="form-check-label" htmlFor="isGroupCheckbox">Is group?</label>
          </div>
          <div className="form-group">
            <label htmlFor="artistImageSelect">Select image</label>
            <input type="file" 
                className="form-control-file" 
                id="artistImageSelect"
                onChange={ this.onFileSelected } />
          </div>
          <div className="row justify-content-center">
            <button 
                className="btn btn-lg btn-primary">
              Add
            </button>
          </div>
        </form>

        { message }
      </div>
    );
  }
};

export default withAdminService()(AddArtistForm);