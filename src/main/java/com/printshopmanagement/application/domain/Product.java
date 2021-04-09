package com.printshopmanagement.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Integer productId;

    @Column
    private String productType;

    @Column
    private String productName;

    @Column
    private Long productQty;

    @Column
    private Long productPrice;
}
