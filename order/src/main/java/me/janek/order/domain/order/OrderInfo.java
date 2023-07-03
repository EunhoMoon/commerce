package me.janek.order.domain.order;

import lombok.Getter;

import java.time.LocalDateTime;

import static me.janek.order.interfaces.order.OrderDto.OrderResponse;

@Getter
public class OrderInfo {

    private final String orderToken;
    private final String productToken;
    private final Integer qty;
    private final Integer unitPrice;
    private final Integer totalPrice;
    private final LocalDateTime createAt;

    public OrderInfo(Order order) {
        this.orderToken = order.getOrderToken();
        this.productToken = order.getProductToken();
        this.qty = order.getQty();
        this.unitPrice = order.getUnitPrice();
        this.totalPrice = order.getTotalPrice();
        this.createAt = order.getCreatedAt();
    }

    public OrderResponse toDto() {
        return new OrderResponse(this);
    }

}
