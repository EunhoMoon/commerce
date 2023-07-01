package me.janek.user.interfaces.user;

import lombok.Getter;

import java.time.LocalDateTime;

public class OrderDto {

    @Getter
    public static class OrderResponse {
        private String orderToken;
        private String productToken;
        private Integer qty;
        private Integer unitPrice;
        private Integer totalPrice;
        private LocalDateTime orderDate;
    }

}
