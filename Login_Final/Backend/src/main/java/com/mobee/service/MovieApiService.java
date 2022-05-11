package com.mobee.service;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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

    public String getInfo(JsonArray list) {

        log.info("서비스 시작" );
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
