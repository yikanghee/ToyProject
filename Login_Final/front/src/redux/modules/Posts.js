// 사용자 정보 관리 모듈
import { createAction, handleActions } from "redux-actions";

// 불변성 관리 패키지
import {produce} from 'immer';

// axios
import axios from 'axios'

// Actions
// 게시판 텍스트를 기록하는 액션
const WRITE_WRITE = "WRITE_WRITE";
// 해당 상세 페이지의 리뷰 리스트를 조회하는 액션
const GET_WRITE = "GET_WRITE";
// 기존 리뷰를 수정하는 액션
const EDIT_WRITE = "EDIT_WRITE";

// Action Creators
// 기록
const writeText = createAction(WRITE_text, (review) => ({review}));
// 조회
const getWrite = createAction(GET_WRITE, (user_comment, review) => ({user_comment, review}));
// 수정
const editWrite = createAction(EDIT_WRITE, (comment_id, review) => ({commnet_id, review}))

// Initial state
const initialState = {

    text: null,
    user_comment: null,
    write_info: {

    }
}

// 텍스트 기록
const setText = (value) => {
    return function (dispatch, getState, {history}){
        dispatchEvent(writeText({
            text: value
        }))
    }
}

const writeReviewAPI = (id) => {
    return function (dispatch, getState, {history}){
        const id = getState.movie.movie_info.id;
        const text = getState().review.text;
        const API = `http://localhost:8000/api/movie/${id}/commnets`;
        const token = localStorage.getItem('is_token');
        if (text === "" || start === 0){
            window.alert('리뷰 내용을 작성해주세요.')
            return
        }
        axios.post(API,
            {
                "comment" : text
            },
            {
                headers: {
                    'Authorization' : `${token}`,
                    'Content-Type' : 'application/json'
                }
            })
            .then((Response) => {
                window.alert('리뷰가 작성되었습니다');
                dispatch(setText(""));
                dispatch(getReviewAPI(id));
            })
            .catch((error) => {
                dispatch(setText(""));
                console.log(error);
                localStorage.removeItem("is_token");
                localStorage.removeItem("login_id")
                window.alert('토큰이 만료되었습니다. 로그인 후 다시 시도해주세요');
                history.push('/login');
            
            })
    }
}

// 페이지에 맞춰 책 리뷰 가져오기
const getReviewAPI = (id) => {
    return function (dispatch, getState, {history}){
        const API = `http://8000/api/movie/${id}/comments/${comment_id}`;
        axios.get(API)
            .then((response) => {
                return response.data
            })

            .then((_review) => {
                if(!_review) {
                    dispatch(getReview(null));
                    return
                }
                // 사용자의 리뷰 찾기
                const username = localStorage.getItem('login_id');
                const commnet_list = _review.comment;
                let user_comment = null
                
                // 코멘트 아이디 찾기
                for (let i = 0; i < commnet_list.length; i++){
                    if(commnet_list[i].account.username === username){
                        user_comment = commnet_list[i]
                    }
                }
                dispatch(getReview(user_comment, _review));
            }).catch((error) => {
                window.alert('리뷰 정보를 불러올 수 없습니다. 다시 시도해주세요.');
                console.log(error);
            })
    }
};

// 리뷰 수정 API
const editReviewAPI = () => {
    return function (dispatch, getState, {history}){
        const comment_id = getState().review.user_comment.id;
        const id = getState().movie.movie_info.id;
        const text = getState().review.text;
        
        if(text === null) {
            window.alert('별점과 내용을 수정해주세요.')
            return
        }
        const API = `http://localhost:8000/api/movie/${id}/comments/${comment_id}`;
        const token = localStorage.getItem('is_token');
        axios.put(API,
            {
                "comment": text,
            },
            {
                headers:{
                    'Authorization': `${token}`,
                    'Content-Type': 'application/json',
                }
            })
            .then((response) => {
                window.alert('리뷰가 수정되었습니다');
                dispatch(setStara)
            })
    }
}

