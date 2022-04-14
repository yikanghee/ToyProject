package Spring_react_01.Spring_react_01.repository;

import Spring_react_01.Spring_react_01.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

    List<Tutorial> findByPublished(boolean published);
    List<Tutorial> findByTitleContaining(String title);

}
