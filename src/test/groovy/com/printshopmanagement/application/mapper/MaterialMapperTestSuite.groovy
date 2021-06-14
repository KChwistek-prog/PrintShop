package com.printshopmanagement.application.mapper

import com.printshopmanagement.application.domain.Material
import com.printshopmanagement.application.domain.MaterialDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
@SpringBootTest
class MaterialMapperTestSuite extends Specification{

    @Autowired
    private MaterialMapper materialMapper

    def avery = new Material()
    def averyDto = new MaterialDto()

    def setup(){
        avery = new Material(1L, "monomer", "Avery", 1, "n/a")
        averyDto = new MaterialDto(1L, "monomer", "Avery", 1, "n/a")
    }

    def "should map material to material dto"(){
        when:
            var result = materialMapper.mapToMaterialDto(avery)
        then:
            result.getMaterialName() == averyDto.getMaterialName()
    }

    def "should map material dto to material"(){
        when:
            var result = materialMapper.mapToMaterial(averyDto)
        then:
            result.getMaterialName() == avery.getMaterialName()
    }

    def "should map material list dto to material list"(){
        given:
            List<Material>materialList = new ArrayList<>()
            materialList.add(avery)
        when:
            var result = materialMapper.mapToMaterialListDto(materialList)
        then:
            result.get(0).getMaterialName() == averyDto.getMaterialName()
    }

}
