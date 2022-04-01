import axios from 'axios';

const ID_KEY = '';
const SECRET_KEY = ;

const api = axios.create({
  baseURL: '/api',
  headers: {
    'X-Naver-Client-Id': '',
    'X-Naver-Client-Secret': ,
    'Access-Control-Allow-Origin': '*'
  }
});

export const naverMoviesApi = {
  search: word => api.get('/v1/search/movie.json', {
    params: {
      query: word,
      display: 20
    }
  })
};