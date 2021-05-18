package com.printshopmanagement.application.controller;

import com.printshopmanagement.application.domain.Equipment;
import com.printshopmanagement.application.domain.EquipmentDto;
import com.printshopmanagement.application.exceptions.EquipmentNotFoundException;
import com.printshopmanagement.application.mapper.EquipmentMapper;
import com.printshopmanagement.application.repository.EquipmentDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class EquipmentController {
    private final EquipmentDbService equipmentDbService;
    private final EquipmentMapper equipmentMapper;

    @Autowired
    public EquipmentController(EquipmentDbService equipmentDbService, EquipmentMapper equipmentMapper) {
        this.equipmentDbService = equipmentDbService;
        this.equipmentMapper = equipmentMapper;
    }

    @PostMapping(value = "/addEquipment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EquipmentDto addEquipment(@RequestBody final EquipmentDto equipmentDto) {
        var persistentEquipment = equipmentDbService.saveEquipment(equipmentMapper.mapToEquipment(equipmentDto));
        return equipmentMapper.mapToEquipmentDto(persistentEquipment);
    }

    @GetMapping(value = "/getEquipment/{id}")
    public EquipmentDto getEquipment(@PathVariable("id") final Long id) throws EquipmentNotFoundException {
        Optional<Equipment> optionalEquipment = equipmentDbService.getEquipment(id);
        return equipmentMapper.mapToEquipmentDto(optionalEquipment.orElseThrow(EquipmentNotFoundException::new));
    }

    @PutMapping(value = "/updateEquipment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EquipmentDto updateEquipment(@RequestBody final EquipmentDto equipmentDto) {
        var persistentEquipment = equipmentDbService.saveEquipment(equipmentMapper.mapToEquipment(equipmentDto));
        return equipmentMapper.mapToEquipmentDto(persistentEquipment);
    }

    @GetMapping(value = "/getEquipments")
    public List<EquipmentDto> getEquipments() {
        return equipmentMapper.mapToEquipmentListDto(equipmentDbService.getAllEquipments());
    }

    @DeleteMapping(value = "/deleteEquipment")
    public void removeEquipment(@RequestParam("id") final Long id) {
        equipmentDbService.deleteEquipment(id);
    }
}
