import React from 'react';

// 라우터
import { Route} from 'react-router-dom';
import { ConnectedRouter } from 'connected-react-router';
import { history } from '../redux/configStore';

// 페이지
import Main from '../pages/Main';
import Login from '../pages/Login';
import Signup from '../pages/Signup';
import CommunityP from '../pages/CommunityP';
import MobeeP from '../pages/MobeeP';
// 부트스트랩
import 'bootstrap/dist/css/bootstrap.min.css';
import { Switch } from 'react-router-dom';

const App = (props) => {
  return (
    <React.Fragment>
      <ConnectedRouter history={history}>
        <Switch>
          <Route path='/' exact component={Main} />
          <Route path='/login' exact component={Login} />
          <Route path='/signup' exact component={Signup} />
          <Route path='/CommunityP' exact component={CommunityP} />
          <Route path='/MobeeP' exact component={MobeeP} />
        </Switch>
      </ConnectedRouter>
    </React.Fragment>
  )
};

export default App;
