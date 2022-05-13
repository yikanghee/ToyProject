package com.mobee.service;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mobee.entity.MovieApi;
import com.mobee.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class MovieApiService {

    private final MovieRepository movieRepository;


    public String getInfo(String result) {

        JsonArray list = null;

        log.info("서비스 시작" );
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(result);
        list = (JsonArray) jsonObject.get("results");

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
        return "ok";
    }

}
