package me.janek.catalog.interfaces.catalog;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDateTime;

public class CatalogDto {

    public static class catalogRequest {

    }

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class catalogResponse {
        private String productId;
        private String productName;
        private Integer unitPrice;
        private Integer totalPrice;
        private LocalDateTime createdAt;
    }

}
