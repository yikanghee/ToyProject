import React from 'react';

// 라우터
import { withRouter } from 'react-router';
import { Route, Link } from 'react-router-dom';

// SCSS
import '../styles/overview.scss';

// 최소 단위 컴포넌트
import { ElSpinner } from '../elements';

// 리덕스 접근
import { useSelector } from 'react-redux';

const overview = (props) => {
    // 책 정보 가져오기
    const { overview } = useSelector(state => state.movies.movie_info);
    // 가져올 때까지 로딩
    if(!overview) {
        return <ElSpinner />
    }
    // 책 소개
    return (
        <div className="overview">
            <h2 className="introduce">책 소개</h2>
            {/* 끝 부분의 계속 읽기는 삭제 */}
            <p className="detail">{overview.split('계속 읽기').[0]}</p>
        </div>
    )
};

export default overview;