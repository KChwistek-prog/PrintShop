package com.printshopmanagement.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDto {
    private Integer productId;
    private String productType;
    private String productName;
    private Long productQty;
    private Long productPrice;
}
