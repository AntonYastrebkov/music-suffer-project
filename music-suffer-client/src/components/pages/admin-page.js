import React from 'react';

import { AddAlbumForm, AddArtistForm } from '../forms';

const AdminPage = () => {
  return (
    <div className="accordeon" id="accordionAdmin">
      <div className="card">
        <div className="card-header" id="headingOne">
          <button className="btn btn-link btn-block text-left" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
            Add album
          </button>
        </div>
        <div id="collapseOne" className="collapse show" aria-labelledby="headingOne" data-parent="#accordionAdmin">
          <div className="card-body">
            <AddAlbumForm />
          </div>
        </div>
      </div>

      <div className="card">
        <div className="card-header" id="headingTwo">
          <button className="btn btn-link btn-block text-left collapsed" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
            Add artist
          </button>
        </div>
        <div id="collapseTwo" className="collapse" aria-labelledby="headingTwo" data-parent="#accordionAdmin">
          <div className="card-body">
            <AddArtistForm />
          </div>
        </div>
      </div>
    </div>
  );
};

export default AdminPage;