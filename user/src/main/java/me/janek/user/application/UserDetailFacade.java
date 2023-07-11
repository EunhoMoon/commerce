package me.janek.user.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.janek.user.application.client.OrderServiceClient;
import me.janek.user.domain.user.UserService;
import org.springframework.stereotype.Service;

import static me.janek.user.interfaces.user.UserDto.UserResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailFacade {

    private final UserService userService;

//    private final RestTemplate restTemplate;
//
//    private final Environment environment;

    private final OrderServiceClient orderServiceClient;

    public UserResponse getUserInfoAndUsersOrderList(String userToken) {
        var findUser = userService.getUserByUserToken(userToken).toDto();

        /* RestTemplate */
//        var orderUrl = String.format(environment.getProperty("order-service.url"), userToken);
//
//        var orderListResponse = restTemplate.exchange(
//            orderUrl,
//            HttpMethod.GET,
//            null,
//            new ParameterizedTypeReference<List<OrderDto.OrderResponse>>() {
//            }
//        );
//        var orderList = orderListResponse.getBody();

        /* Feign Client */
        var orderList = orderServiceClient.getOrders(userToken);
        findUser.setUserOrderList(orderList);

        return findUser;
    }

}
