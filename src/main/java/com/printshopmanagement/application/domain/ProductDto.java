package com.printshopmanagement.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDto {
    private final Long productId;
    private final String productType;
    private final String productName;
    private final Long productQty;
    private final Long productPrice;
}
