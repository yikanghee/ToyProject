import React from 'react';

// SCSS
import "../styles/header.scss";

// 리덕스
import { useSelector } from 'react-redux';
// 로그아웃
import { actionCreators as userActions } from "../redux/modules/user";
import { useDispatch } from "react-redux";

// 페이지 이동을 위한 history
import { history } from '../redux/configStore';


// 헤더 컴포넌트
const Header = (props) => {
  const dispatch = useDispatch();
  // 상태 업데이트 할 dispatch 가져오기

  // 로그인 여부 가져오기
  const is_login = useSelector(state => state.user.is_login);
  // 스토어에서 itemReducer로 등록된 상태 가져오기

  // 로그아웃
  const logOut = () => {
    dispatch(userActions.logoutStorage());
    //dispatch를 이용해서 action을 리듀서에 전달
  }

  React.useEffect(() => {
    dispatch(userActions.logInCheckStorage());
  }, [])
  // 이벤트가 있을때 로그인 체크

  return (
    <React.Fragment>
      <div className="header">
        <nav className="navbar navbar-expand-md navbar-light">
          <a className="navbar-brand"
            // 클릭시 메인으로 돌아가기
            onClick={() => { history.push('/') }}
          ><span className="logo-bold">MOBEE</span></a>
          <ul className="navbar-nav mr-auto">
            <li className="nav-item">
              <a className="nav-link"
                // 클릭시 메인
                onClick={() => { history.push('/') }}
              ><span className="logo-bold">영화</span></a>
            </li>
          </ul>
          <ul className="navbar-nav">
            <li className="nav-item">
              {/* 로그인 상태이면 로그아웃 표시하기 */}
              {is_login ?
                (<a className="nav-link"
                  onClick={logOut}
                >로그아웃</a>)
                :
                (<a className="nav-link"
                  onClick={() => {
                    { history.push('/login') }
                  }}
                >로그인</a>)
              }

            </li>
          </ul>

        </nav>
      </div>
      <hr className="headerLine"></hr>
    </React.Fragment>
  )
};

export default Header;