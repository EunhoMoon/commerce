package me.janek.order.domain.order;

import java.util.List;

public interface OrderService {

    OrderInfo createOrder(OrderCommand command);

    OrderInfo getOrderByOrderToken(String orderToken);

    List<OrderInfo> getAllOrdersByUserToken(String userToken);

}
