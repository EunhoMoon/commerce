package me.janek.user.interfaces.user;

import lombok.RequiredArgsConstructor;
import me.janek.user.domain.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static me.janek.user.interfaces.user.UserDto.RegisterRequest;
import static me.janek.user.interfaces.user.UserDto.UserResponse;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping()
  public ResponseEntity<UserResponse> userRegistration(@RequestBody RegisterRequest request) {
    var command = request.toCommand();
    var createdUserInfo = userService.createUser(command).toDto();

    return ResponseEntity.status(CREATED).body(createdUserInfo);
  }

}
