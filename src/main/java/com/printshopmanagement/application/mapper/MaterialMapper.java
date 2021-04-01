package com.printshopmanagement.application.mapper;

import com.printshopmanagement.application.domain.Material;
import com.printshopmanagement.application.domain.MaterialDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialMapper {

    public Material mapToMaterial(final MaterialDto materialDto) {
        return new Material(
                materialDto.getId(),
                materialDto.getMaterialType(),
                materialDto.getMaterialName(),
                materialDto.getMaterialQty(),
                materialDto.getAdditionalComments()
        );
    }

    public MaterialDto mapToMaterialDto(final Material material) {
        return new MaterialDto(
                material.getId(),
                material.getMaterialType(),
                material.getMaterialName(),
                material.getMaterialQty(),
                material.getAdditionalComments()
        );
    }

    public List<MaterialDto> mapToMaterialListDto(final List<Material> materials) {
        return materials.stream()
                .map(this::mapToMaterialDto)
                .collect(Collectors.toList());
    }

}
