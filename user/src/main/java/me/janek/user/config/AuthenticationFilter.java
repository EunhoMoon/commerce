package me.janek.user.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.janek.user.domain.user.UserService;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;
import static me.janek.user.interfaces.user.UserDto.LoginRequest;

@Slf4j
@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserService userService;

    private final Environment environment;

    @Override
    public Authentication attemptAuthentication(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws AuthenticationException {
        try {
            var loginRequest = getLoginRequest(request);
            var token = getToken(loginRequest);

            return getAuthenticationManager().authenticate(token);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    protected void successfulAuthentication(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain,
        Authentication authResult
    ) {
        var username = ((User) authResult.getPrincipal()).getUsername();
        var findUserDetail = userService.getUserDetailsByEmail(username);
        var jwtToken = generateJwtToken(findUserDetail.getUserToken());

        response.addHeader("token", jwtToken);
        response.addHeader("userId", findUserDetail.getUserToken());
    }

    private String generateJwtToken(String userToken) {
        var expirationTime = environment.getProperty("token.expiration_time", Long.class);
        return Jwts.builder()
            .setSubject(userToken)
            .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
            .signWith(HS512, environment.getProperty("token.secret", String.class))
            .compact();
    }

    private LoginRequest getLoginRequest(HttpServletRequest request) throws IOException {
        return new ObjectMapper()
            .readValue(request.getInputStream(), LoginRequest.class);
    }

    private UsernamePasswordAuthenticationToken getToken(LoginRequest loginRequest) {
        return new UsernamePasswordAuthenticationToken(
            loginRequest.getEmail(),
            loginRequest.getPassword(),
            new ArrayList<>()
        );
    }

}
