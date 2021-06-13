package com.printshopmanagement.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String materialType;

    @Column
    private String materialName;

    @Column
    private Integer materialQty;

    @Column
    private String comments;

    public Material(String materialType, String materialName, Integer materialQty, String comments) {
        this.materialType = materialType;
        this.materialName = materialName;
        this.materialQty = materialQty;
        this.comments = comments;
    }
}
