import React from 'react';
import { Switch, Route } from 'react-router-dom';

import Header from '../header';
import { AdminPage, AlbumPage, MainPage, AlbumDetails } from '../pages';
import Spinner from '../spinner';

const App = () => {
  return (
    <div className="container">
      <Header />
      <Switch>
        <Route path="/"
            component={MainPage}
            exact/>
        <Route path="/albums/" exact
            component={AlbumPage} />
        <Route path="/albums/:id"
            render={({match}) => {
              return <AlbumDetails id={match.params.id} />
            }} />
        <Route path="/artists"
            component={Spinner} />
        <Route path="/login"
            component={Spinner} />
        <Route path="/admin/management"
            component={AdminPage} />
        <Route render={() => <h2>404: Page not found</h2>} />
      </Switch>
    </div>
  );
};

export default App;