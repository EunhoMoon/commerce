package me.janek.user.interfaces.user;

import lombok.RequiredArgsConstructor;
import me.janek.user.domain.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static me.janek.user.interfaces.user.UserDto.RegisterRequest;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/")
  public String userRegistration(@RequestBody RegisterRequest request) {
    var command = request.toCommand();
    userService.createUser(command);

    return "User registration is success";
  }

}
