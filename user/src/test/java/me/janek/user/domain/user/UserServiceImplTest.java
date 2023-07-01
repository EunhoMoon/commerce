package me.janek.user.domain.user;

import me.janek.user.infrastructure.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    void init() {
        var newUser = UserCommand.builder()
            .email("init-user@co.kr")
            .name("init user")
            .password("12341234")
            .userToken(UUID.randomUUID().toString())
            .build();

        userService.createUser(newUser);
    }

    @AfterEach
    void clean() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("회원 정상 등록 및 해당 토큰을 통한 조회 성공")
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
        var findUser = userRepository.findByUserToken(command.getUserToken())
            .orElseThrow(RuntimeException::new);
        assertEquals("test@co.kr", findUser.getEmail());
    }

    @Test
    @DisplayName("회원 전체 조회 성공")
    void test2() {
        // when
        var allUsers = userService.getAllUsers();

        // expect
        assertEquals(allUsers.size(), 1);
    }

}