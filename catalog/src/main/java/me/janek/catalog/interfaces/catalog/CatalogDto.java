package me.janek.catalog.interfaces.catalog;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import me.janek.catalog.domain.catalog.CatalogInfo;

import java.time.LocalDateTime;

public class CatalogDto {

    public static class catalogRequest {

    }

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CatalogResponse {
        private final String productId;
        private final String productName;
        private final Integer unitPrice;
//        private final Integer totalPrice;
        private final LocalDateTime createdAt;

        public CatalogResponse(CatalogInfo catalogInfo) {
            this.productId = catalogInfo.getProductToken();
            this.productName = catalogInfo.getProductName();
            this.unitPrice = catalogInfo.getUnitPrice();
//            this.totalPrice = catalogInfo.getTotalPrice();
            this.createdAt = catalogInfo.getCreatedAt();
        }
    }

}
