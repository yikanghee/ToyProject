import React from 'react';

// 라우터
import { withRouter } from 'react-router';
import { Route, Link } from 'react-router-dom';

// SCSS
import '../styles/book-info.scss';

// 머터리얼 ui
import FavoriteBorderIcon from '@material-ui/icons/FavoriteBorder';
import FavoriteIcon from '@material-ui/icons/Favorite';
import Button from '@material-ui/core/Button';

// 리덕스 접근
import { useSelector, useDispatch } from 'react-redux';

// 리덕스
import { actionCreators as heartActions } from "../redux/modules/heart";

// elements
import Star from '../elements/Star';

// 최소 단위 컴포넌트
import { ElSpinner } from '../elements';