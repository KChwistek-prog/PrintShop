package com.printshopmanagement.application.controller;

import com.printshopmanagement.application.domain.EquipmentDto;
import com.printshopmanagement.application.exceptions.EquipmentNotFoundException;
import com.printshopmanagement.application.mapper.EquipmentMapper;
import com.printshopmanagement.application.repository.EquipmentDbService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class EquipmentController {
    private EquipmentDbService dbService;
    private EquipmentMapper equipmentMapper;

    @PostMapping(value = "/addEquipment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EquipmentDto addEquipment(@RequestBody final EquipmentDto equipmentDto) {
        var persistentEquipment = dbService.saveEquipment(equipmentMapper.mapToEquipment(equipmentDto));
        return equipmentMapper.mapToEquipmentDto(persistentEquipment);
    }

    @GetMapping(value = "/getEquipment/{id}")
    public EquipmentDto getEquipment(@PathVariable("id") final Long id) throws EquipmentNotFoundException {
        if (dbService.getEquipment(id).isPresent()) {
            return equipmentMapper.mapToEquipmentDto(dbService.getEquipment(id).get());
        } else throw new EquipmentNotFoundException();
    }

    @PutMapping(value = "/updateEquipment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EquipmentDto updateEquipment(@RequestBody final EquipmentDto equipmentDto) {
        var persistentEquipment = dbService.saveEquipment(equipmentMapper.mapToEquipment(equipmentDto));
        return equipmentMapper.mapToEquipmentDto(persistentEquipment);
    }

    @GetMapping(value = "/getEquipments")
    public List<EquipmentDto> getEquipments() {
        return equipmentMapper.mapToEquipmentListDto(dbService.getAllEquipments());
    }

    @DeleteMapping(value = "/deleteEquipment")
    public void removeEquipment(@RequestParam("id") final Long id) {
        dbService.deleteEquipment(id);
    }
}
