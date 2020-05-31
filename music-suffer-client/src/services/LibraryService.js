export default class LibraryService {
  _libraryApiBase = 'http://localhost:8080/api/library';

  _getResource = async (url) => {
    const result = await fetch(`${this._libraryApiBase}${url}`);

    if (!result.ok) {
      throw new Error(`Could not fetch ${url}, status code: ${result.status}`);
    }
    const body = await result.json();
    return body;
  };

  getAlbums = async () => {
    const res = await this._getResource("/album/");
    return res;
  };

  getAlbumsTest = () => {
    return [
      {
        id: 1,
        name: "Space Odyssey",
        coverPath: "",
        year: "1969"
      },
      {
        id: 2,
        name: "Dark Side of the Moon",
        coverPath: "",
        year: "1973"
      }
    ];
  };
}