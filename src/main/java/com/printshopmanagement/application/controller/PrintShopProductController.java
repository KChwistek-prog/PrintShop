package com.printshopmanagement.application.controller;

import com.printshopmanagement.application.domain.ProductDto;
import com.printshopmanagement.application.exceptions.ProductNotFoundException;
import com.printshopmanagement.application.mapper.ProductMapper;
import com.printshopmanagement.application.repository.ProductDbService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class PrintShopProductController {
    private final ProductDbService dbService;
    private final ProductMapper productMapper;


    public PrintShopProductController(ProductDbService dbService, ProductMapper productMapper) {
        this.dbService = dbService;
        this.productMapper = productMapper;
    }

    @PutMapping(value = "/addProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        var persistentProduct = dbService.saveProduct(productMapper.mapToProduct(productDto));
        return productMapper.mapToProductDto(persistentProduct);
    }

    @GetMapping(value = "/getProduct/{id}")
    public ProductDto getProduct(@PathVariable("id") Integer id) throws ProductNotFoundException {
        var persistentTask = dbService.getProduct(id);
        if (persistentTask.isPresent()) {
            return productMapper.mapToProductDto(persistentTask.get());
        } else throw new ProductNotFoundException();
    }

    @GetMapping(value = "/getProductList")
    public List<ProductDto> getProducts() {
        return productMapper.mapToProductDtoList(dbService.getAllProducts());
    }

    @PutMapping(value = "/updateProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto updateProducts(@RequestBody ProductDto productDto) {
        var persistentProduct = dbService.saveProduct(productMapper.mapToProduct(productDto));
        return productMapper.mapToProductDto(persistentProduct);
    }

    @DeleteMapping(value = "/deleteProduct")
    public void removeProduct(@RequestParam Integer id) {
        dbService.deleteProduct(id);
    }
}
