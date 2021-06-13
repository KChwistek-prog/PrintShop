package com.printshopmanagement.application.repository

import com.printshopmanagement.application.domain.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class ProductRepositoryTestSuite extends Specification{

    @Autowired
    private ProductDbService productDbService

    def "should add product to database"(){
        given:
            def banner = new Product(1L, "banner", "banner", 1, 5)
        when:
            productDbService.saveProduct(banner)
        then:
            productDbService.getProduct(1L).isPresent()
            productDbService.getProduct(1L).get().getProductName() == "banner"
    }

    def "should return list of products"(){
        given:
            def banner = new Product(1L, "banner", "banner", 1, 5)
            def monomer = new Product(2L , "monomer", "monomer", 1, 5)
            productDbService.saveProduct(banner)
            productDbService.saveProduct(monomer)
        when:
            var result = productDbService.getAllProducts()
        then:
            result.size() == 2
    }

    def "should return product from database"(){
        given:
            def banner = new Product(1L, "banner", "banner", 1, 5)
            productDbService.saveProduct(banner)
        when:
            var result = productDbService.getProduct(1L)
        then:
            result.isPresent()
            result.orElseThrow().getProductName() == "banner"
    }

    def "should remove product from database"(){
        given:
            def banner = new Product(1L, "banner", "banner", 1, 5)
            productDbService.saveProduct(banner)
        when:
            productDbService.deleteProduct(1L)
        then:
            productDbService.getProduct(1L).isEmpty()
    }
}
