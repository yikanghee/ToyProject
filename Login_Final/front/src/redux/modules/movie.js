// 책 정보 모듈
import { createAction, handleActions } from "redux-actions";
// 불변성 관리 패키지
import { produce } from "immer";

// axios
import axios from 'axios';

// 리뷰 액션
import { actionCreators as reviewActions } from "./review";
// 좋아요 액션
import { actionCreators as heartActions } from './heart';

// Action
// 보여줄 책 리스트들 다룰 액션
const SET_MOVIES = "SET_MOVIES";
// 현재 페이지, 시작 또는 끝 페이지를 다루는 액션
const UPDATE_CURRENT = "UPDATE_CURRENT";
const UPDATE_START_END = "UPDATE_START_END";
// 상세 페이지 책 정보를 다룰 액션
const GET_MOVIE_INFO = "GET_MOVIE_INFO";

// Action Creators
const setMovies = createAction(SET_MOVIES, (movie_list) => ({ movie_list}));
const updateCurrent = createAction(UPDATE_CURRENT, (paging) => ({paging}));
const updateStartEnd = createAction(UPDATE_START_END, (paging) => ({paging}));
const getMovieInfo = createAction(GET_MOVIE_INFO, (movie_info) => ({movie_info}));

// Intial State
const initialState = {
    // 책 목록을 담는 배열
    movie_list: [],
    // 페이지네이션 초기값
    paging: {
      // 시작
      start: 1,
      // 끝
      end: 10,
      // 현재 페이지
      current: 1,
    },
    // 디테일 페이지의 영화 정보
    movie_info: {
      // id: 'id',
      // imgUrl: 'imgUrl',
      // title: 'title',
    
    }
  }

  // 페이지 이동
  // 현재 페이지
  const updateCurrentPage = (page) => {
      return function (dispatch, getState, {history}){
          // 리덕스에 현재 페이지 저장
          dispatch(updateCurrent({
              current: page
          }));
          // 서버로부터 북 리스트 가져오기
          dispatch(MovieListAPI());
      }
  };

    // 처음, 끝 페이지
    const updateStartEndPage = (start, end) => {
    return function (dispatch, getState, { history }) {
      dispatch(updateStartEnd({
        start: start,
        end: end,
      }))
    }
  }

    // 페이지에 맞춰 책 리스트 가져오기
    const MovieListAPI = (select) => {
        return function (dispatch, getState, {history}){
            let current = getState().movies.paging.currnet;
            // select가 좋아요, 별점순인 경우
            if (select === "heart" || select === "starRate") {
                current = 1;
            }
            
            const API = `http://localhost:8000/api/movies?sort=${select}&page=${current}&size=24`;
            axios.get(API)
            .then((response) =>{
                return response.data
            })
            .then((_movie_list) => {
                // 리덕스에 담기
                dispatch(setMovies(_movie_list));
            }).catch((error) => {

            });
        }
    };

    const MovieInfoAPI = (movie_id) => {
        return function (dispatch, getState, { history }) {
            const API = `http://localhost:8000/api/movies/${movie_id}`;
            axios.get(API)
                .then((response) => {
                    return response.data
                })
                .then((_movie_info) => {
                    //리덕스에 담기
                    dispatch(getMovieInfo(_movie_info));
                })
                .then(
                    // 좋아요 정보 가져오기
                    dispatch(heartActions.getHeartAPI(movie_id))
                )
                .then(
                    //리뷰 정보 가져오기
                    dispatch(reviewActions.getReviewAPI(movie_id))
                )
                .catch((error) => {
                    window.alert('책 정보를 불러오지 못했습니다. 재시도해주세요');
                });

        }
    }

    // Reducers
    export default handleActions(
    {
      [SET_MOVIES]: (state, action) =>
        produce(state, (draft) => {
          draft.movie_list = action.payload.movie_list;
        }),
      [UPDATE_CURRENT]: (state, action) =>
        produce(state, (draft) => {
          draft.paging.current = action.payload.paging.current;
        }),
      [UPDATE_START_END]: (state, action) =>
        produce(state, (draft) => {
          draft.paging.start = action.payload.paging.start;
          draft.paging.end = action.payload.paging.end;
        }),
      [GET_MOVIE_INFO]: (state, action) =>
        produce(state, (draft) => {
          draft.movie_info = action.payload.movie_info;
        })
    },
    initialState
  );

    // Action Creators export
    const actionCreators = {
        updateCurrentPage,
        updateStartEndPage,
        MovieListAPI,
        MovieInfoAPI,
    }

    export { actionCreators };
