package com.printshopmanagement.application.controller;

import com.printshopmanagement.application.domain.MaterialDto;
import com.printshopmanagement.application.exceptions.MaterialNotFoundException;
import com.printshopmanagement.application.mapper.MaterialMapper;
import com.printshopmanagement.application.repository.MaterialDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class MaterialController {
    private final MaterialMapper materialMapper;
    private final MaterialDbService materialDbService;

    @Autowired
    public MaterialController(MaterialMapper materialMapper, MaterialDbService materialDbService) {
        this.materialMapper = materialMapper;
        this.materialDbService = materialDbService;
    }

    @PostMapping(value = "/addMaterial", consumes = MediaType.APPLICATION_JSON_VALUE)
    public MaterialDto addMaterial(@RequestBody final MaterialDto materialDto) {
        var persistMaterial = materialDbService.saveMaterial(materialMapper.mapToMaterial(materialDto));
        return materialMapper.mapToMaterialDto(persistMaterial);
    }

    @PutMapping(value = "/updateMaterial", consumes = MediaType.APPLICATION_JSON_VALUE)
    public MaterialDto updateMaterial(@RequestBody MaterialDto materialDto) {
        var persisMaterial = materialDbService.saveMaterial(materialMapper.mapToMaterial(materialDto));
        return materialMapper.mapToMaterialDto(persisMaterial);
    }

    @GetMapping(value = "/getMaterial/{id}")
    public MaterialDto getMaterial(@PathVariable("id") final Long id) throws MaterialNotFoundException {
        if (materialDbService.getMaterial(id).isPresent()) {
            return materialMapper.mapToMaterialDto(materialDbService.getMaterial(id).get());
        } else throw new MaterialNotFoundException();
    }

    @GetMapping(value = "/getMaterials")
    public List<MaterialDto> getMaterials() {
        return materialMapper.mapToMaterialListDto(materialDbService.getAllMaterials());
    }

    @DeleteMapping(value = "/deleteMaterial")
    public void removeMaterial(@RequestParam("id") final Long id) {
        materialDbService.deleteMaterial(id);
    }

}
