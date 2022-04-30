from urllib.request import urlopen
from bs4 import BeautifulSoup
from flask import Flask, jsonify
from flask_restful import reqparse, abort, Api, Resource
app = Flask(__name__)
app.config['JSON_AS_ASCII'] = False

api = Api(app)


@app.route('/')
def hello_world():
    return "Movie API"


@app.route('/api/crawlingMovieInfo')
def crawlingMovieInfo():
    try:
        url = "https://movie.naver.com/movie/running/current.naver"
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

            movieInfo = {"title": title, "code": code, "comment":comments, "movie_type":movie_type, "imgUrl":imgUrl + str(code)}
            movieInfoList.append(movieInfo)
        # result["movieInfo"] = movieInfoList
        result["movieInfo"] = movieInfoList;


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
            for i in range(1, 10):
                review = soup.find('span', {'id': f'_filtered_ment_{i}'})
                review = review.get_text().strip()
                review_list.append(review)
        result = "".join(review_list)  # 문자열로 변환

        return result

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
