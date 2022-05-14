//package com.mobee.service;
//
//import com.mobee.entity.Heart;
//import com.mobee.repository.AccountRepository;
//import com.mobee.repository.HeartRepository;
//import com.mobee.repository.MovieRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class HeartService {
//
//    private final HeartRepository heartRepository;
//    private final MovieRepository movieRepository;
//    private final AccountRepository accountRepository;
//
//    public HashMap<String, Object> ReadHeart(Long movie_id, Long account_id) {
//        Heart heart = heartRepository.findByMovieApiAndAccount_Id(movie_id, account_id);
//        List<Heart> heartCount = heartRepository.findByMovieApi(movie_id);
//        HashMap<String, Object> map = new HashMap<>();
//        Integer Count = heartCount.size();
//
//        if (heart == null) {
//            map.put("check", false);
//            map.put("")
//        }
//    }
//}
