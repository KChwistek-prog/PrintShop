package com.printshopmanagement.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long productId;

    @Column
    private String productType;

    @Column
    private String productName;

    @Column
    private Integer productQty;

    @Column
    private Integer productPrice;

    public Product(String productType, String productName, Integer productQty, Integer productPrice) {
        this.productType = productType;
        this.productName = productName;
        this.productQty = productQty;
        this.productPrice = productPrice;
    }
}
