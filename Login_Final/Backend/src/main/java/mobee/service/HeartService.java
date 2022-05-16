package mobee.service;

import lombok.RequiredArgsConstructor;
import mobee.entity.Account;
import mobee.entity.Heart;
import mobee.entity.MovieApi;
import mobee.repository.AccountRepository;
import mobee.repository.HeartRepository;
import mobee.repository.MovieRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final MovieRepository movieRepository;
    private final AccountRepository accountRepository;

    public HashMap<String, Object> ReadHeart(Long movie_id, Long account_id) {
        Heart heart = heartRepository.findByMovieApi_IdAndAccount_Id(movie_id, account_id);
        List<Heart> heartCount = heartRepository.findByMovieApi_Id(movie_id);
        HashMap<String, Object> map = new HashMap<>();
        Integer Count = heartCount.size();

        if (heart == null) {
            map.put("check", false);
            map.put("heartCount", Count);
            return map;
        }
        map.put("check", true);
        map.put("heartCount", Count);
        return map;
    }

    @Transactional
    public Heart CreateHeart(Long movie_id, Long account_id) {
        MovieApi movie = movieRepository.findById(movie_id).orElseThrow(
                () -> new IllegalArgumentException("영화가 존재하지 않습니다")
        );
        Account account = accountRepository.findById(account_id).orElseThrow(
                () -> new IllegalArgumentException("계정이 존재하지 않습니다")
        );

        Heart heart = heartRepository.findByMovieApi_IdAndAccount_Id(movie_id, account_id);

        if (heart == null) {
            Heart newHeart = new Heart();
            newHeart.setHeart(true);
            movie.addHeart(newHeart);
            account.addHeart(newHeart);
            heartRepository.save(newHeart);
            return newHeart;
        }
        return null;
    }

    public Heart DeleteHeart(Long movie_id, Long account_id) {
        MovieApi movie = movieRepository.findById(movie_id).orElseThrow(
                () -> new IllegalArgumentException("책이 존재하지 않습니다")
        );
        Account account = accountRepository.findById(account_id).orElseThrow(
                () -> new IllegalArgumentException("계정이 존재하지 않습니다.")
        );
        Heart heart = heartRepository.findByMovieApi_IdAndAccount_Id(movie_id, account_id);
        if (heart != null) {
            movie.deleteHeart(heart);
            account.deleteHeart(heart);
            heartRepository.deleteById(heart.getId());
            return heart;
        }
        return null;
    }

}
