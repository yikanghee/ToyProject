import React from "react";
import {Link} from 'react-router-dom';
import PropTypes from "prop-types";
import "./Movie.css";

function Movie({id, year, title, summary, poster, genres, rating}) {
  return (
    <div className="movie">
    <Link to={{
          pathname: `/movie/detail/${id}`,
          state:{
            id,
            year,
            title,
            summary,
            poster,
            genres,
            rating
          }
    }}>
      <img src={poster} alt={title} titlt={title}></img>
    <div className="movie__data">
      <h3 className="movie__title">{title}</h3>
    <h4 className="movie__rating">{rating}/10</h4>
      <h5 className="movie__year">{year}</h5>
      <ul className="movie__genres">
            {genres.map((genre, index) => (
              <li key={index} className="genres__genre">
                {genre}
              </li>
            ))}
          </ul>
    <p className="movie__summary">{summary.slice(0,180)}...</p>
    </div>
  </Link>
  </div>
  )
}

Movie.propTypes = {
  id: PropTypes.number.isRequired,
  year: PropTypes.number.isRequired,
  title: PropTypes.string.isRequired,
  summary: PropTypes.string.isRequired,
  poster: PropTypes.string.isRequired,
  genres: PropTypes.arrayOf(PropTypes.string).isRequired,
  rating: PropTypes.number.isRequired

};

export default Movie;