package me.janek.user.interfaces.user;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

  private final Environment environment;

  @GetMapping("/health_check")
  public String status() {
    return "It's Working in User Service";
  }

  @GetMapping("/welcome")
  public String welcome() {
    return environment.getProperty("greeting.message");
  }

}
