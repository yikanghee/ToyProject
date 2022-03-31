import React from 'react';
import './Movie.scss';

const MovieCard = () => {
    return (
        <div className="movie-card-container">
            <div className="movie-image"></div>
            <div className="movie-text">
                <h2>제목</h2>
                <div>년도</div>
                <div className="movie-summary-row">
                    <h5>평점</h5>
                </div>
                <div className="movie-likes">/ 10</div>
            </div>
        </div>
    );
};
export default MovieCard;