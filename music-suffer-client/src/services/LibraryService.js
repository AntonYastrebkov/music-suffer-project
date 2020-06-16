export default class LibraryService {
  albums = [
    {
      id: "1",
      name: 'Space Oddity',
      coverPath: 'so.jpg',
      year: '1969',
      averageScore: 9.8,
      genre: 'ROCK',
      artist: {
        id: "5",
        name: "David Bowie"
      },
      compositions: [
        {
          id: "1",
          position: 1,
          name: "Space Oddity"
        },
        {
          id: "2",
          position: 2,
          name: "Unwashed and Somewhat Slightly Dazed"
        },
        {
          id: "3",
          position: 3,
          name: "Letter to Hermione"
        },
        {
          id: "4",
          position: 4,
          name: "Cygnet Comittee"
        },
        {
          id: "5",
          position: 5,
          name: "Janine"
        },
        {
          id: "6",
          position: 6,
          name: "An Occasional Dream"
        },
        {
          id: "7",
          position: 7,
          name: "Wild Eyes Boy from Freecloud"
        },
        {
          id: "8",
          position: 8,
          name: "Conversation Piece"
        },
        {
          id: "9",
          position: 9,
          name: "God Knows I'm Good"
        },
        {
          id: "10",
          position: 10,
          name: "Memory of a Free Festival"
        },
      ]
    },
    {
      id: "2",
      name: 'Led Zeppelin IV',
      coverPath: 'lz.jpg',
      year: '1969',
      averageScore: 10.0,
      genre: 'ROCK',
      artist: {
        id: "5",
        name: "David Bowie"
      },
      compositions: [
        {
          id: "1",
          position: 1,
          name: "Letter to Hermione"
        }
      ]
    },
    {
      id: "3",
      name: 'Sabbath Bloody Sabbath',
      coverPath: 'sbs.jpg',
      year: '1969',
      averageScore: 10.0,
      genre: 'ROCK',
      artist: {
        id: "5",
        name: "David Bowie"
      },
      compositions: [
        {
          id: "1",
          position: 1,
          name: "Letter to Hermione"
        }
      ]
    },
    {
      id: "4",
      name: 'AAAAAAA',
      coverPath: 'sbs.jpg',
      year: '1969',
      averageScore: 10.0,
      genre: 'ROCK',
      artist: {
        id: "5",
        name: "David Bowie"
      },
      compositions: [
        {
          id: "1",
          position: 1,
          name: "Letter to Hermione"
        }
      ]
    },
    {
      id: "5",
      name: 'BBBBBBB',
      coverPath: 'sbs.jpg',
      year: '1969',
      averageScore: 10.0,
      genre: 'ROCK',
      artist: {
        id: "5",
        name: "David Bowie"
      },
      compositions: [
        {
          id: "1",
          position: 1,
          name: "Letter to Hermione"
        }
      ]
    },
    {
      id: "6",
      name: 'CCCCCCC',
      coverPath: 'sbs.jpg',
      year: '1969',
      averageScore: 10.0,
      genre: 'ROCK',
      artist: {
        id: "5",
        name: "David Bowie"
      },
      compositions: [
        {
          id: "1",
          position: 1,
          name: "Letter to Hermione"
        }
      ]
    },
    {
      id: "7",
      name: 'DDDDDDD',
      coverPath: 'sbs.jpg',
      year: '1969',
      averageScore: 10.0,
      genre: 'ROCK',
      artist: {
        id: "5",
        name: "David Bowie"
      },
      compositions: [
        {
          id: "1",
          position: 1,
          name: "Letter to Hermione"
        }
      ]
    },
    {
      id: "8",
      name: 'EEEEEEEE',
      coverPath: 'sbs.jpg',
      year: '1969',
      averageScore: 10.0,
      genre: 'ROCK',
      artist: {
        id: "5",
        name: "David Bowie"
      },
      compositions: [
        {
          id: "1",
          position: 1,
          name: "Letter to Hermione"
        }
      ]
    },
    {
      id: "9",
      name: 'FFFFFFFFFFFFFFFFF',
      coverPath: 'sbs.jpg',
      year: '1969',
      averageScore: 10.0,
      genre: 'ROCK',
      artist: {
        id: "5",
        name: "David Bowie"
      },
      compositions: [
        {
          id: "1",
          position: 1,
          name: "Letter to Hermione"
        }
      ]
    },
  ];
  _libraryApiBase = 'http://localhost:8080/api/library';
  _imageBase = 'http://localhost:8080/img/';

  _getResource = async (url) => {
    const result = await fetch(`${this._libraryApiBase}${url}`);

    if (!result.ok) {
      throw new Error(`Could not fetch ${url}, status code: ${result.status}`);
    }
    const body = await result.json();
    return body;
  };

  getAlbums = async (page) => {
    // const res = await this._getResource("/album/");
    // return res;
    console.log('SERVICE', page);
    return new Promise((resolve, reject) => {
      setTimeout(() => {
        let content, current;
        switch(page.page) {
          case 0:
            content = this.albums.slice(0, 3);
            current = 0;
            break;
          case 1:
            content = this.albums.slice(3, 6);
            current = 1;
            break;
          case 2:
            content = this.albums.slice(6);
            current = 2;
            break;
          default:
            content = this.albums;
        }
        content.forEach(c => c.coverPath = `${this._imageBase}${c.coverPath}`);
        resolve({
          content: content,
          paging: {
            page: current,
            totalPages: 3,
            size: 12,
            sort: 'id',
            direction: 'desc',
            first: true,
            last: false
          }
        });
      }, 900)
    });
  };

  getAlbum = async (id) => {
    return this.albums.find((a) => a.id === id);
  };

  getComments = async (page, id) => {
    return [
      {
        id: 100,
        content: "Awesome",
        authorId: 1234,
        authorName: "John Doe",
        score: 10,
        createdAt: "..."
      },
      {
        id: 101,
        content: "Awful",
        authorId: 1234,
        authorName: "John Doe",
        score: 1,
        createdAt: "..."
      },
      {
        id: 102,
        content: "I'm a teapot",
        authorId: 1,
        authorName: "Teapot",
        score: 10,
        createdAt: "..."
      },
      {
        id: 666,
        content: "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum ",
        authorId: 1,
        authorName: "Teapot",
        score: 10,
        createdAt: "..."
      }
    ];
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