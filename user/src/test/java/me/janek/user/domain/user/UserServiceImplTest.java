package me.janek.user.domain.user;

import me.janek.user.infrastructure.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class UserServiceImplTest {

  @Autowired
  UserService userService;

  @Autowired
  UserRepository userRepository;

  @Test
  @DisplayName("")
  void test() {
    //given
    var command = UserCommand.builder()
      .email("test@co.kr")
      .name("test")
      .password("12341234")
      .userToken(UUID.randomUUID().toString())
      .build();

    // when
    userService.createUser(command);

    // then
    var allUsers = userRepository.findAll();
    assertEquals(1, allUsers.size());
    assertEquals("test@co.kr", allUsers.get(0).getEmail());
  }

}