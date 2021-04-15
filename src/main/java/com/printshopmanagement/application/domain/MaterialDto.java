package com.printshopmanagement.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MaterialDto {
    private final Long id;
    private final String materialType;
    private final String materialName;
    private final Long materialQty;
    private final String comments;
}
