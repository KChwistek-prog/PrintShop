package com.printshopmanagement.application.mapper;

import com.printshopmanagement.application.domain.Product;
import com.printshopmanagement.application.domain.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductMapper {
    public Product mapToProduct(ProductDto productDto) {
        return new Product(
                productDto.getProductId(),
                productDto.getProductType(),
                productDto.getProductName(),
                productDto.getProductQty(),
                productDto.getProductPrice()
        );
    }

    public ProductDto mapToProductDto(Product product){
        return new ProductDto(
                product.getProductId(),
                product.getProductType(),
                product.getProductName(),
                product.getProductQty(),
                product.getProductPrice()
        );
    }

    public List<ProductDto> mapToProductDtoList(List<Product> productList){
        return productList.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }

}
