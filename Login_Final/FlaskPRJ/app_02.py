from urllib.request import urlopen
from bs4 import BeautifulSoup
from flask import Flask, jsonify
from flask_restful import reqparse, abort, Api, Resource
from datetime import datetime
app = Flask(__name__)
app.config['JSON_AS_ASCII'] = False

api = Api(app)


@app.route('/')
def hello_world():
    return "Movie API"


@app.route('/crawlingMovieInfo')
def crawlingMovieInfo():
    try:
        url = "https://www.netflix.com/kr/browse/genre/5685"
        html = urlopen(url)
        soup = BeautifulSoup(html, 'html.parser')

        movieList = soup.find('ul', 'lst_detail_t1')

        result = {}

        movieInfo = {}
        movieInfoList = []

        for i in range(0, 10):
            code = movieList.findAll('div', 'thumb')[i].find('a')["href"][30:]
            comments = crawlingComments(code=code)
            movie_type = crawlingType(code=code)
            title = movieList.findAll('dt', 'tit')[i].find('a').getText()
            imgUrl = movieList.findAll('div', 'thumb')[i].find('img')['src']

            movieInfo = {"title": title, "comments": comments, "movie_type": movie_type, "imgUrl":imgUrl, }
            movieInfoList.append(movieInfo)
            # with urlopen(imgUrl) as f:
            #     with open("../SpringPRJ/WebContent/resources/assets/movieImg/" + str(code) + ".jpg", "wb") as h:
            #         img = f.read()
            #         h.write(img)
        # result["movieInfo"] = movieInfoList
        result["movieInfo"] = movieInfoList;

        result["date"] = datetime.today();

        # f.close()
        # h.close()

        return jsonify(result)

    except Exception as e:
        return {'error': str(e)}


def crawlingComments(code):
    try:
        review_list = []
        for page in range(1, 12):
            url = f'https://movie.naver.com/movie/bi/mi/pointWriteFormList.nhn?code={code}&type=after&isActualPointWriteExecute=false&isMileageSubscriptionAlready=false&isMileageSubscriptionReject=false&page={page}'
            html = urlopen(url)
            soup = BeautifulSoup(html, 'html.parser')

        for i in range (1, 3):
            movie_contents = soup.find({'class' : 'con_tx'}).text(i)

        movie_content = "";
        for i in movie_contents:
            movie_content = movie_content + i.getText

        return movie_content

    except Exception as e:
        return "No Comments"

def crawlingType(code):
    url = "https://movie.naver.com/movie/bi/mi/basic.naver?code={}".format(code)
    html = urlopen(url)
    soup = BeautifulSoup(html, "html.parser")

    movie_typeList = soup.find('dl', {'class': 'info_spec'}).find('span').findAll("a")
    movie_type = ""
    for i in movie_typeList:
        movie_type = movie_type + i.getText();

    return movie_type



if __name__ == '__main__':
    app.run()
    app.run(debug=True, host="127.0.0.1", port=5000)