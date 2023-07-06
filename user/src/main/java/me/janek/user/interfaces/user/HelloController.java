package me.janek.user.interfaces.user;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class HelloController {

    private final Environment env;

    @GetMapping("/health-check")
    public String status() {
        return "It's Working in User Service"
            + ", port(local.server.port) = " + env.getProperty("local.server.port")
            + ", port(server.port) = " + env.getProperty("server.port")
            + ", token secret = " + env.getProperty("token.secret")
            + ", token expiration time = " + env.getProperty("token.expiration_time");
    }

    @GetMapping("/welcome")
    public String welcome() {
        return env.getProperty("greeting.message");
    }

}
