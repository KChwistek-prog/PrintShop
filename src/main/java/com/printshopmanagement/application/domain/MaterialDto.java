package com.printshopmanagement.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MaterialDto {
    private Long id;
    private String materialType;
    private String materialName;
    private Integer materialQty;
    private String comments;
}
