package Spring_react_01.Spring_react_01.repository;

import Spring_react_01.Spring_react_01.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}
