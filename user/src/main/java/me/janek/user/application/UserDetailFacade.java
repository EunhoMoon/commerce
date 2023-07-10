package me.janek.user.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.janek.user.domain.user.UserService;
import me.janek.user.interfaces.user.OrderDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static me.janek.user.interfaces.user.UserDto.UserResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailFacade {

    private final UserService userService;

    private final RestTemplate restTemplate;

    private final Environment environment;

    public UserResponse getUserInfoAndUsersOrderList(String userToken) {
        var findUser = userService.getUserByUserToken(userToken).toDto();
        log.info("userToken = {}", userToken);
        log.info(environment.getProperty("order-service"));
        var orderUrl = String.format(environment.getProperty("order-service.url"), userToken);

        var orderListResponse = restTemplate.exchange(
            orderUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<OrderDto.OrderResponse>>() {
            }
        );

        findUser.setUserOrderList(orderListResponse.getBody());

        return findUser;
    }

}
