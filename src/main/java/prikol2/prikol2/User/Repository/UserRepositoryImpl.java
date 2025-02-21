package prikol2.prikol2.User.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prikol2.prikol2.User.User;

import java.util.Optional;

@Repository
public interface UserRepositoryImpl extends JpaRepository<User, Long> {
    Optional<User> findUserByUserName(String userName);

    Boolean existsByUserName(String userName);

    Boolean existsByLogin(String login);
}
