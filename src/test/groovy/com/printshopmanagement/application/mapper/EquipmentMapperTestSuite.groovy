package com.printshopmanagement.application.mapper

import com.printshopmanagement.application.domain.Equipment
import com.printshopmanagement.application.domain.EquipmentDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import java.sql.Timestamp
import java.time.LocalDate

@SpringBootTest
class EquipmentMapperTestSuite extends Specification{

    @Autowired
    private EquipmentMapper equipmentMapper

    def plotter = new Equipment()
    def plotterDto = new EquipmentDto()

    def setup(){
        plotter = new Equipment(1L, "Roland", "Online", new Timestamp((LocalDate.now() - 3).toEpochDay()),"n/c")
        plotterDto = new EquipmentDto(1L, "Roland", "Online", new Timestamp((LocalDate.now() - 3).toEpochDay()),"n/c")
    }

    def "should map equipment to equipment dto"(){
        when:
            var result = equipmentMapper.mapToEquipmentDto(plotter)
        then:
            result.getEquipmentName() == plotterDto.getEquipmentName()
    }

    def "should map equipment dto to equipment"(){
        when:
            var result = equipmentMapper.mapToEquipment(plotterDto)
        then:
            result.getEquipmentName() == plotter.getEquipmentName()
    }

    def "should map equipment list to  equipment dto list"(){
        given:
        List<Equipment> equipList = new ArrayList<>()
        equipList.add(plotter)
        when:
        var result = equipmentMapper.mapToEquipmentListDto(equipList)
        then:
        result.get(0).getEquipmentName() == plotterDto.getEquipmentName()
    }
}
