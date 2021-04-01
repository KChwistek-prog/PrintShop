package com.printshopmanagement.back.controller;

import com.printshopmanagement.back.domain.ProductDto;
import com.printshopmanagement.back.exceptions.ProductNotFoundException;
import com.printshopmanagement.back.mapper.ProductMapper;
import com.printshopmanagement.back.repository.DbService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class PrintShopProductController {
    private final DbService dbService;
    private final ProductMapper productMapper;

    public PrintShopProductController(DbService dbService, ProductMapper productMapper) {
        this.dbService = dbService;
        this.productMapper = productMapper;
    }

    @PutMapping(value = "/addProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        var persistentProduct = dbService.saveProduct(productMapper.mapToProduct(productDto));
        return productMapper.mapToProductDto(persistentProduct);
    }

    @GetMapping(value = "/getProduct/{id}")
    public ProductDto getProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
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
    public void removeProduct(@RequestParam Long id) {
        dbService.deleteProduct(id);
    }
}
