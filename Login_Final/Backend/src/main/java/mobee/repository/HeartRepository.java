package mobee.repository;

import mobee.entity.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    Heart findByMovieApi_IdAndAccount_Id(Long movie_id, Long account_id);

    List<Heart> findByMovieApi_Id(Long movie_id);
}
