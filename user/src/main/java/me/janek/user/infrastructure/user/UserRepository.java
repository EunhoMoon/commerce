package me.janek.user.infrastructure.user;

import me.janek.user.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserToken(String userToken);

}
