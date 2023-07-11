package me.janek.user.application.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static me.janek.user.interfaces.user.OrderDto.OrderResponse;

@FeignClient(name = "order-service")
public interface OrderServiceClient {

    @GetMapping("/order-service/{user-token}/orders")
    List<OrderResponse> getOrders(@PathVariable("user-token") String userToken);

}
