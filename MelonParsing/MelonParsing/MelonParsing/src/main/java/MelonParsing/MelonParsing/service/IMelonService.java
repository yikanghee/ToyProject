package MelonParsing.MelonParsing.service;

import MelonParsing.MelonParsing.dto.MelonDTO;

import java.util.List;

public interface IMelonService {

    /**
     * 멜론 노래 리스트 저장하기
     */
    int collectMelonSong() throws Exception;


    /**
     * 멜론 가수별 노래 수 가져오기
     */
    List<MelonDTO> getSingerSongCnt() throws Exception;

    /**
     * 가수의 노래 가져오기
     *
     * @return 노래 리스트
     */
    List<MelonDTO> getSingerSong() throws Exception;

    /**
     * 멜론 노래 리스트 한번에 저장하기
     */
    int collectMelonSongMany() throws Exception;

}
