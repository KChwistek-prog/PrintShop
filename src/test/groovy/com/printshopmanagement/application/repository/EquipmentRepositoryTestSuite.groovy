package com.printshopmanagement.application.repository

import com.printshopmanagement.application.domain.Equipment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import java.sql.Timestamp

@SpringBootTest
class EquipmentRepositoryTestSuite extends Specification{

    @Autowired
    private EquipmentDbService equipmentDbService

    def "should save new item into database"(){
        given:
        def printer = new Equipment(1L,"Printer", "Online", new Timestamp(20001212121212), "n/c")
        when:
        equipmentDbService.saveEquipment(printer)
        then:
        equipmentDbService.getEquipment(1L).isPresent()
        equipmentDbService.getEquipment(1L).get().getEquipmentName() == "Printer"
    }

    def "should return equipment list"(){
        given:
            def HpPrinter = new Equipment(1L,"HpPrinter", "Online", new Timestamp(20001212121212), "n/c")
            def RolandPrinter = new Equipment(2L,"RolandPrinter", "Online", new Timestamp(20001212121212), "n/c")
            equipmentDbService.saveEquipment(HpPrinter)
            equipmentDbService.saveEquipment(RolandPrinter)
        when:
            var resultList = equipmentDbService.getAllEquipments()
        then:
            resultList.size() == 2
    }

    def "database should return printer"(){
        given:
            def printer = new Equipment(1L,"Printer", "Online", new Timestamp(20001212121212), "n/c")
        equipmentDbService.saveEquipment(printer)
        when:
            var result = equipmentDbService.getEquipment(1L)
        then:
            result.orElseThrow().getEquipmentName() == "Printer"
    }

    def "should remove item from database"(){
        given:
            def printer = new Equipment(1L,"Printer", "Online", new Timestamp(20001212121212), "n/c")
            equipmentDbService.saveEquipment(printer)
        when:
            equipmentDbService.deleteEquipment(1L)
        then:
            equipmentDbService.getEquipment(1L).isEmpty()
    }
}
