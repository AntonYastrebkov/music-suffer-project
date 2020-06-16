export default class AdminService {
  _apiBasePath = 'http://localhost:8080/api/admin';
  _streamingBasePath = 'http://localhost:8080/api/streaming';

  _postMusic = async (path, body) => {
    const result = await fetch(`${this._streamingBasePath}${path}`, {
      method: 'POST',
      mode: 'cors',
      headers: {
        "Authorization": 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiZmlyc3ROYW1lIjoiSm9obiIsImxhc3ROYW1lIjoiRG9lIiwicm9sZXMiOlsiQURNSU4iXSwiaXNFbmFibGVkIjp0cnVlLCJleHAiOjE2NzU2MDE2MjIsImlhdCI6MTU4OTIwMTYyMiwiZW1haWwiOiJhQG1haWwuY29tIn0.05SIH8oESRZmNASuR1TxmZ3YvatFAElLWKw01z4ijlR35tUNDIYzhYMHojSmzQk1DTsSQ2UsE3Kl9ABJqIDRcw'
      },
      body: body
    });

    return result;
  };

  _postRequest = async (path, body, params) => {
    const result = await fetch(`${this._apiBasePath}${path}?${params}`, {
      method: 'POST',
      mode: 'cors',
      headers: {
        "Authorization": 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiZmlyc3ROYW1lIjoiSm9obiIsImxhc3ROYW1lIjoiRG9lIiwicm9sZXMiOlsiQURNSU4iXSwiaXNFbmFibGVkIjp0cnVlLCJleHAiOjE2NzU2MDE2MjIsImlhdCI6MTU4OTIwMTYyMiwiZW1haWwiOiJhQG1haWwuY29tIn0.05SIH8oESRZmNASuR1TxmZ3YvatFAElLWKw01z4ijlR35tUNDIYzhYMHojSmzQk1DTsSQ2UsE3Kl9ABJqIDRcw'
      },
      body: body
    });

    return result;
  };

  _getRequest = async (path, body, params = '') => {
    const result = await fetch(`${this._apiBasePath}${path}?${params}`, {
      method: 'GET',
      mode: 'cors',
      headers: {
        "Authorization": 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiZmlyc3ROYW1lIjoiSm9obiIsImxhc3ROYW1lIjoiRG9lIiwicm9sZXMiOlsiQURNSU4iXSwiaXNFbmFibGVkIjp0cnVlLCJleHAiOjE2NzU2MDE2MjIsImlhdCI6MTU4OTIwMTYyMiwiZW1haWwiOiJhQG1haWwuY29tIn0.05SIH8oESRZmNASuR1TxmZ3YvatFAElLWKw01z4ijlR35tUNDIYzhYMHojSmzQk1DTsSQ2UsE3Kl9ABJqIDRcw'
      },
      body: body
    });

    return result;
  };

  getGenres = async () => {
    const result = await this._getRequest('/genres', );
    return result.json();
  }

  addAlbum = async (album, image, compositions) => {
    const formBody = new FormData();
    const musicDTOs = compositions.map((c, idx) => {
      return {
        position: idx + 1,
        name: c.name
      };
    });
    album.compositions = musicDTOs;
    formBody.append('album', JSON.stringify(album));
    formBody.append('image', image);
    const addResponse = await this._postRequest('/album', formBody);
    addResponse.json()
      .then((data) => {
        data.forEach(element => {
          const formBody = new FormData();
          const file = compositions[element.position - 1].file;
          formBody.append('file', file);
          formBody.append('musicId', element.id);
          this._postMusic('/music', formBody);
        });
      })
  };

  getArtistList = async (artistName) => {
    const param = `name=${artistName}`;
    const result = await this._getRequest('/artist', null, param);
    return result.json();
  }

  addArtist = async (artist, image) => {
    const formBody = new FormData();
    formBody.append('artist', JSON.stringify(artist));
    formBody.append('image', image);
    const res = await this._postRequest("/artist", formBody);
    
    return res;
  }
}