import React, { Fragment } from 'react';
import { Input } from "antd";
import MovieCard from '../Component/MovieCard';
const { Search } = Input;

const MovieSearchContainer = () => {
    return(
    <Fragment>
        <div
            style={{ display: 'flex', justifyContent: 'center', padding: "2rem"}}
        >
            <Search
                placeholder="영화를 검색해보세요!"
                onSearch={(value) => console.log(value)}
                style={{width: 200}}
            />
        </div>
        <div>
            <MovieCard />
        </div>
    </Fragment>
    );
};
export default MovieSearchContainer;