package az.azerenerji.repository;

import az.azerenerji.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
  /*
  SElect*
  FROM user
  WHERE e
   */

   User findUsersByEmail(String email);
}
