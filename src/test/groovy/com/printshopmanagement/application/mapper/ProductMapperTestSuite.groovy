package com.printshopmanagement.application.mapper

import com.printshopmanagement.application.domain.Product
import com.printshopmanagement.application.domain.ProductDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class ProductMapperTestSuite extends Specification{

    @Autowired
    private ProductMapper productMapper

    def banner = new Product()
    def bannerDto = new ProductDto()

    def setup(){
        banner = new Product(1L, "banner", "banner", 1, 5)
        bannerDto = new ProductDto(1L, "banner", "banner", 1, 5)
    }

    def "should map product to product dto"(){
        when:
        var result = productMapper.mapToProductDto(banner)
        then:
        result.getProductName() == bannerDto.getProductName()
    }

    def "should map product dto to product"(){
        when:
        var result = productMapper.mapToProduct(bannerDto)
        then:
        result.getProductName() == banner.getProductName()
    }

    def "should map product list to product dto list"(){
        given:
        List<Product>productList = new ArrayList<>()
        productList.add(banner)
        when:
        var result = productMapper.mapToProductDtoList(productList)
        then:
        result.get(0).getProductName() == bannerDto.getProductName()
    }

}
