package com.printshopmanagement.application.repository

import com.printshopmanagement.application.domain.Material
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class MaterialRepositoryTestSuite extends Specification{

    @Autowired
    private MaterialDbService materialDbService

    def "should add material type to database"(){
        given:
            def avery = new Material(1L, "monomer", "Avery", 1, "n/a")
        when:
            materialDbService.saveMaterial(avery)
        then:
            materialDbService.getMaterial(1L).isPresent()
            materialDbService.getMaterial(1L).orElseThrow().getMaterialName() == "Avery"
    }

    def "should return list of materials"(){
        given:
            def avery = new Material(1L, "monomer", "Avery", 1, "n/a")
            def oracal = new Material(2L, "monomer", "Oracal", 1, "n/a")
            materialDbService.saveMaterial(avery)
            materialDbService.saveMaterial(oracal)
        when:
            var result = materialDbService.getAllMaterials()
        then:
            result.size() == 2
    }

    def "should return material from database"(){
        given:
            def avery = new Material(1L, "monomer", "Avery", 1, "n/a")
            materialDbService.saveMaterial(avery)
        when:
            var result = materialDbService.getMaterial(1L)
        then:
            result.isPresent()
            result.orElseThrow().getMaterialName() == "Avery"
    }

    def "should remove material from database"(){
        given:
            def avery = new Material(1L, "monomer", "Avery", 1, "n/a")
            materialDbService.saveMaterial(avery)
        when:
            materialDbService.deleteMaterial(1L)
        then:
            materialDbService.getMaterial(1L).isEmpty()
    }
}
