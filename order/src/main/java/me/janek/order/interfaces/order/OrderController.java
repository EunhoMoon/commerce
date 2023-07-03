package me.janek.order.interfaces.order;

import lombok.RequiredArgsConstructor;
import me.janek.order.domain.order.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static me.janek.order.interfaces.order.OrderDto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-service")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{user-token}/orders")
    public ResponseEntity<OrderResponse> createOrder(
        @PathVariable("user-token") String userToken,
        @RequestBody OrderRequest request
    ) {
        var orderCreate = request.toCommand(userToken);
        var createdOrder = orderService.createOrder(orderCreate);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder.toDto());
    }

    @GetMapping("/{user-token}/orders")
    public ResponseEntity<List<OrderResponse>> getOrders(
        @PathVariable("user-token") String userToken
    ) {
        var usersOrderList = orderService.getAllOrdersByUserToken(userToken)
            .stream().map(OrderResponse::new)
            .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(usersOrderList);
    }

}
