package com.ecompra.microservices.product_service.mapper;

import com.ecompra.microservices.product_service.dto.ProductDto;
import com.ecompra.microservices.product_service.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public ProductDto mapProductToProductDto (Product product) {
        return ProductDto.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public Product mapProductDtoToProduct(ProductDto productDto) {
        return Product.builder()
                .name(productDto.name())
                .description(productDto.description())
                .price(productDto.price())
                .build();
    }
}
