import React from 'react';

// SCSS
import '../styles/navigator.scss';

// 리덕스
import { useSelector } from 'react-redux';
// 로그아웃
import { actionCreators as userActions } from "../redux/modules/user";
import { useDispatch } from "react-redux";

// 페이지 이동을 위한 history
import { history } from '../redux/configStore';



// 네비게이터 컴포넌트
const Navigator = (props) => {

  const dispatch = useDispatch();
  // 로그인 여부 가져오기
  const is_login = useSelector(state => state.user.is_login);

  // 로그아웃
  const logOut = () => {
    dispatch(userActions.logoutStorage());
  }

  React.useEffect(() => {
    dispatch(userActions.logInCheckStorage());
  }, [])

  return (
    <div>
      <div className="navigator">
        <nav className="navbar navbar-expand-md navbar-light">
          <ul className="navbar-nav mr-auto">

          <li className="nav-item">
              <a className="nav-link"
                // 클릭시 메인
                onClick={() => { history.push('/Main')}}
              ><span className="logo-bold">커뮤니티</span></a>
            </li>

            <li className="nav-item">
              <a className="nav-link" href="#com">커뮤니티</a>
            </li>

            <li className="nav-item">              
              <a className="nav-link" href="#mobeew">영화관 찾기</a>
            </li>

            <li className="nav-item">
              <a className="nav-link" href="#chat">채팅</a>
            </li>

          </ul>
        </nav>
      </div>

    </div>
  )
};

export default Navigator;