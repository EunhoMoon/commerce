package me.janek.catalog.domain.catalog;

import lombok.Getter;

import java.time.LocalDateTime;

import static me.janek.catalog.interfaces.catalog.CatalogDto.CatalogResponse;

@Getter
public class CatalogInfo {

    private String productToken;

    private String productName;

    private Integer unitPrice;

//    private Integer totalPrice;

    private LocalDateTime createdAt;

    public CatalogInfo(Catalog catalog) {
        this.productToken = catalog.getProductToken();
        this.productName = catalog.getProductName();
        this.unitPrice = catalog.getUnitPrice();
//        this.totalPrice = catalog.getTo;
        this.createdAt = catalog.getCreatedAt();
    }

    public CatalogResponse toDto() {
        return new CatalogResponse(this);
    }

}
