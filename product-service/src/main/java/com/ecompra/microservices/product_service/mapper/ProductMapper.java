package com.ecompra.microservices.product_service.mapper;

import com.ecompra.microservices.product_service.dto.ProductDto;
import com.ecompra.microservices.product_service.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public ProductDto mapProductToProductDto (Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .build();
    }

    public Product mapProductDtoToProduct(ProductDto productDto) {
        return Product.builder()
                .id(productDto.id())
                .name(productDto.name())
                .description(productDto.description())
                .price(productDto.price())
                .category(productDto.category())
                .build();
    }
}
