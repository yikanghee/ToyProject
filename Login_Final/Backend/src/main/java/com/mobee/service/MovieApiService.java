package com.mobee.service;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mobee.entity.MovieApi;
import com.mobee.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@RequiredArgsConstructor
@Service
@Slf4j
public class MovieApiService {

    private final String KEY = "bf4027c3100b9e4e2dc3221cfb994433";

    private String result = "";

    private final MovieRepository movieRepository;

    public int getPages(String type) {
        int page = 0 ;

        log.info("getPage Service 시작!");

        try {
            URL url = new URL("https://api.themoviedb.org/3/discover/" + type + "?api_key="
                    + KEY + "&release_date.gte=2010-01-01&watch_region=KR&language=ko");

            BufferedReader bf;

            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            result = bf.readLine();

            JsonParser jsonParser = new JsonParser();

            JsonObject jsonObject = (JsonObject) jsonParser.parse(result);

            String pages = jsonObject.get("total_pages").toString();

            page = Integer.parseInt(pages);

        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("getPage Service 끝!");

        return page;
    }

    public Boolean getInfo(String type) {

        int pages = getPages(type);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = "0001-01-01";

        try {

            for (int i = 1; i <= pages; i++) {
                String apiURL = "https://api.themoviedb.org/3/discover/" + type + "?api_key" + KEY
                        + "&release_date.gte=2010-01-01&watch_region=KR&language=ko&page=" + i;

                URL url = new URL(apiURL);

                BufferedReader bf;

                bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

                result = bf.readLine();

                JsonParser jsonParser = new JsonParser();
                JsonObject jsonObject = (JsonObject) jsonParser.parse(result);
                JsonArray list = (JsonArray) jsonObject.get("results");

                for (int k = 0; k < list.size(); k++) {
                    JsonObject contents = (JsonObject) list.get(k);
                    movieRepository.save(
                            MovieApi.builder()
                                    .contents_num(Long.parseLong(String.valueOf(contents.get("id"))))
                                    .overview(contents.get("overview").toString())
                                    .vote_average(Float.parseFloat(String.valueOf(contents.get("vote_average"))))
                                    .title(contents.get("title").toString())
                                    .poster_path(contents.get("poster_path").toString())
                                    .build()
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }
}
