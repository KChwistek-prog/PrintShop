package com.printshopmanagement.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MaterialDto {
    private Long id;
    private String materialType;
    private String materialName;
    private Long materialQty;
    private String additionalComments;
}
