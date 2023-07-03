package me.janek.order.domain.order;

import lombok.RequiredArgsConstructor;
import me.janek.order.infrastructure.order.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public OrderInfo createOrder(OrderCommand command) {
        var createdOrder = command.toEntity();

        orderRepository.save(createdOrder);

        return new OrderInfo(createdOrder);
    }

    @Override
    public OrderInfo getOrderByOrderToken(String orderToken) {
        var findOrder = orderRepository.findByOrderToken(orderToken)
            .orElseThrow(RuntimeException::new);

        return new OrderInfo(findOrder);
    }

    @Override
    public List<OrderInfo> getAllOrdersByUserToken(String userToken) {
        var userOrderList = orderRepository.findByUserToken(userToken);

        return userOrderList.stream().map(OrderInfo::new)
            .collect(Collectors.toList());
    }

}
