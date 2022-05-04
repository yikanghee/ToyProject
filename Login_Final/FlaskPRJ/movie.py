from selenium import webdriver
from time import sleep

from bs4 import BeautifulSoup

from movie

@app.route('/')
def hello_world():
    return "Movie API"

@app.route('/api/crawlingMovieInfo')
def crawlingMovieInfo():
    try:
        url = "https://www.themoviedb.org"



main_url = "https://www.themoviedb.org"

for category in ["tv", "mv"]:
    if category == "tv":
        url_path = list(db.tv_url.find({}, {"_id": False}))
    if category == "mv":
        url_path = list(db.movie_url.find({}, {"_id": False}))

    for i in range(0, 50):

        # ChromeDriver로 접속, 자원 로딩시간 3초
        driver = webdriver.Chrome('./chromedriver')

        url = main_url + url_path[i]['url_path']
        driver.get(url)  # 드라이버에 해당 url의 웹페이지를 띄웁니다.
        sleep(1)  # 페이지가 로딩되는 동안 1초 간 기다립니다.

        req = driver.page_source  # html 정보를 가져옵니다.
        driver.quit()  # 정보를 가져왔으므로 드라이버는 꺼줍니다.

        soup = BeautifulSoup(req, 'html.parser')

        header = soup.select_one('#original_header')

        poster_path = header.select_one('div.poster img.poster')['src']
        poster_url = main_url + poster_path

        info = header.select_one('section.header')

        title = info.select_one('h2 > a').text

        runtime = info.select_one('div.facts > span.runtime').text.strip()

        genres = info.select('div.facts > span.genres > a')
        genre = ""

        for g in genres:
            genre = genre + " " + g.text

        doc = {
            "net_poster": poster_url,
            "net_title": title,
            "net_cate": category,
            "net_runtime": runtime,
            "net_genre": genre,
            "net_rv_count": 0
        }

        db.netflix.insert_one(doc)