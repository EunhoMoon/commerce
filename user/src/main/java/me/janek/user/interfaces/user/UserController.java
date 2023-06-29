package me.janek.user.interfaces.user;

import lombok.RequiredArgsConstructor;
import me.janek.user.domain.user.UserCommand;
import me.janek.user.domain.user.UserService;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import static me.janek.user.interfaces.user.UserDto.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

  private final Environment environment;

  private final UserService userService;

  @GetMapping("/health_check")
  public String status() {
    return "It's Working in User Service";
  }

  @GetMapping("/welcome")
  public String welcome() {
    return environment.getProperty("greeting.message");
  }

  @PostMapping("/users")
  public String userRegistration(@RequestBody RegisterRequest request) {
    var command = request.toCommand();
    userService.createUser(command);

    return "User registration is success";
  }

}
