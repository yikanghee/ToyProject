package mobee.service;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import mobee.entity.MovieApi;
import mobee.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
@Slf4j
public class MovieApiService {

    private final MovieRepository movieRepository;

    LocalDateTime dateTime = LocalDateTime.now();

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
                            .description(contents.get("overview").toString())
                            .vote_average(Float.parseFloat(String.valueOf(contents.get("vote_average"))))
                            .title(contents.get("title").toString())
                            .imgUrl(contents.get("poster_path").toString())
                            .createdAt(dateTime)
                            .modifiedAt(dateTime)
                            .build()
            );
        }
        return "ok";
    }

}
