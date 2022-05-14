package mobee.repository;

import mobee.entity.MovieApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface MovieRepository extends JpaRepository<MovieApi, Long> {

    Page<MovieApi> findAllByOrderByCreatedAtDesc(Pageable pageable);

    @Query(value = "select A.* , B.average_vote " +
            "from book as A " +
            "left outer join (select contents_num, round(avg(star_rate),1) as average_vote from comment group by contents_num) as B " +
            "on A.id = B.contents_num order by average_vote is null asc, average_vote desc" +
            "limit :start, :offset"
            ,nativeQuery = true)
    List<MovieApi> findAllByOrderByStarRate(int start, int offset);

    @Query(value = "select A.*, B.total " +
            "from book as A " +
            "left outer join(select contents_num, sum(IF(is_heart = true, 1, 0)) as total from heart group by contents_num) as B " +
            "on A.id = B.contents_num order by total is null asc, total desc" +
            "limit :start, :offset"
            ,nativeQuery = true)
    List<MovieApi> findAllByOrderByHeart(int start, int offset);

}
