package me.janek.user.domain.user;

import lombok.RequiredArgsConstructor;
import me.janek.user.infrastructure.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  @Transactional
  public void createUser(UserCommand command) {
    var encryptedPassword = "encrypted_password";
    var createdUser = command.toEntity(encryptedPassword);

    userRepository.save(createdUser);
  }

}
