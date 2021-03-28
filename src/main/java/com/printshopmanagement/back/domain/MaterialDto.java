package com.printshopmanagement.back.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;

@Getter
@AllArgsConstructor
public class MaterialDto {
    private Long id;
    private String materialType;
    private String materialName;
    private Long materialQty;
    private String additionalComments;
}
