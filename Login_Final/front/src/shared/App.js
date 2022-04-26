import React from 'react';

// 라우터
import { Route} from 'react-router-dom';
import { ConnectedRouter } from 'connected-react-router';
import { history } from '../redux/configStore';

// 페이지
import Login from '../pages/Login';
import Signup from '../pages/Signup';


// 부트스트랩
import 'bootstrap/dist/css/bootstrap.min.css';
import { Switch } from 'react-router-dom';

const App = (props) => {
  return (
    <React.Fragment>
      <ConnectedRouter history={history}>
        <Switch>
          <Route path='/login' exact component={Login} />
          <Route path='/signup' exact component={Signup} />
        </Switch>
      </ConnectedRouter>
    </React.Fragment>
  )
};

export default App;
