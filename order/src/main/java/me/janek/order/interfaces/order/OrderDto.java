package me.janek.order.interfaces.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import me.janek.order.domain.order.OrderCommand;
import me.janek.order.domain.order.OrderInfo;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrderDto {

    @Data
    public static class OrderRequest {

        private String productToken;
        private Integer qty;
        private Integer unitPrice;

        public OrderCommand toCommand(String userToken) {
            var orderToken = UUID.randomUUID().toString();

            return OrderCommand.builder()
                .orderToken(orderToken)
                .productToken(this.productToken)
                .qty(this.qty)
                .unitPrice(this.unitPrice)
                .totalPrice(this.unitPrice * this.qty)
                .userToken(userToken)
                .build();
        }
    }

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class OrderResponse {

        private final String orderToken;
        private final String productToken;
        private final Integer qty;
        private final Integer unitPrice;
        private final Integer totalPrice;
        private final LocalDateTime orderDate;

        public OrderResponse(OrderInfo orderInfo) {
            this.orderToken = orderInfo.getOrderToken();
            this.productToken = orderInfo.getProductToken();
            this.qty = orderInfo.getQty();
            this.unitPrice = orderInfo.getUnitPrice();
            this.totalPrice = orderInfo.getTotalPrice();
            this.orderDate = orderInfo.getCreateAt();
        }
    }

}
