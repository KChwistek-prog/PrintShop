package com.printshopmanagement.back.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String productType;
    private String productName;
    private Long productQty;
    private Long productPrice;
}
