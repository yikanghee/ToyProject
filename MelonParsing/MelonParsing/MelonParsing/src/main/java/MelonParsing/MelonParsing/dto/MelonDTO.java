package MelonParsing.MelonParsing.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class MelonDTO {

    String collectTime; // 수집 시간
    String song; // 노래 제목
    String singer; // 가수
    int singerCnt; // 차트에 등록된 가수별 노래 수

}