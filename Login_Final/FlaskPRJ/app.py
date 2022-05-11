import os
from flask import Flask, jsonify
from flask_restful import reqparse, abort, Api, Resource
import requests

app = Flask(__name__)
app.config['JSON_AS_ASCII'] = False

api = Api(app)

TMDB_API_KEY = str(os.getenv('bf4027c3100b9e4e2dc3221cfb994433'))


@app.route('/')
def hello_world():
    return "Movie API"


@app.route('/hello')
def hello():
    return "dddd"


@app.route('/crawlingMovieInfo')
def get_movie_datas():
    global data
    for i in range(1, 501):

        print("dddd")

        request_url = f"<https://api.themoviedb.org/3/movie/popular?api_key={TMDB_API_KEY}&language=ko-KR&page={i}>"
        movies = requests.get(request_url).json()

        for movie in movies['results']:
            if movie.get('release_date', ''):
                data = {
                    'movie_id': movie['id'],
                    'title': movie['title'],
                    'released_date': movie['release_date'],
                    'popularity': movie['popularity'],
                    'vote_avg': movie['vote_average'],
                    'overview': movie['overview'],
                    'poster_path': movie['poster_path'],
                    'genres': movie['genre_ids']
                }

    return jsonify(data)


if __name__ == '__main__':
    app.run()
    app.run(debug=True, host="127.0.0.1", port=5000)
