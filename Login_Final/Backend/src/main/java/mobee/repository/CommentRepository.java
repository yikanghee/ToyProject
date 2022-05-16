package mobee.repository;

import mobee.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByMovieApiIdOrderByCreatedAtDesc(Long movie_id);

    Comment findByAccountIdAndMovieApiId(Long account_id, Long movie_id);

    List<Comment> findByMovieApiId(Long movie_id);

}
