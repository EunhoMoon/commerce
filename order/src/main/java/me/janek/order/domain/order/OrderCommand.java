package me.janek.order.domain.order;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderCommand {

    private final String orderToken;
    private final String productToken;
    private final Integer qty;
    private final Integer unitPrice;
    private final Integer totalPrice;
    private final String userToken;

    @Builder
    public OrderCommand(
        String orderToken,
        String productToken,
        Integer qty,
        Integer unitPrice,
        Integer totalPrice,
        String userToken
    ) {
        this.orderToken = orderToken;
        this.productToken = productToken;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.userToken = userToken;
    }

    public Order toEntity() {
        return Order.builder()
            .orderToken(this.orderToken)
            .productToken(this.productToken)
            .qty(this.qty)
            .totalPrice(this.totalPrice)
            .unitPrice(this.unitPrice)
            .userToken(this.userToken)
            .build();
    }
}
