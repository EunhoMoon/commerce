package me.janek.user.domain.user;

import lombok.RequiredArgsConstructor;
import me.janek.user.infrastructure.user.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final BCryptPasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public UserInfo createUser(UserCommand command) {
    var encryptedPassword = passwordEncoder.encode(command.getPassword());
    var createdUser = command.toEntity(encryptedPassword);

    userRepository.save(createdUser);

    return new UserInfo(createdUser);
  }

}
