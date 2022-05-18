import React from 'react';

// 라우터
import { Route} from 'react-router-dom';
import { ConnectedRouter } from 'connected-react-router';
import { history } from '../redux/configStore';

// 페이지
import Main from '../pages/Main';
import Login from '../pages/Login';
import Detail from '../pages/Detail';

// 부트스트랩
import 'bootstrap/dist/css/bootstrap.min.css';
import { Switch } from 'react-router-dom';
import MovieInfo from '../components/MovieInfo';

const App = (props) => {
  return (
    <React.Fragment>
      <ConnectedRouter history={history}>
        <Switch>
          <Route path='/' exact component={Main} />
          <Route path='/login' exact component={Login} />
          <Route path='/test' exact component={MovieInfo} />
          <Route path='/Detail/:id' exact component={Detail} />
        </Switch>
      </ConnectedRouter>
    </React.Fragment>
  )
};

export default App;