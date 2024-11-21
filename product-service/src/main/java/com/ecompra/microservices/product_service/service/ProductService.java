package com.ecompra.microservices.product_service.service;

import com.ecompra.microservices.product_service.dto.ProductDto;
import com.ecompra.microservices.product_service.mapper.ProductMapper;
import com.ecompra.microservices.product_service.model.Product;
import com.ecompra.microservices.product_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductDto createProduct(ProductDto productDto) {
        Product product = productMapper.mapProductDtoToProduct(productDto);
        return productMapper.mapProductToProductDto(productRepository.save(product));
    }

    public List<ProductDto> getProducts() {
        return productRepository.findAll().stream().map(productMapper::mapProductToProductDto).collect(Collectors.toList());
    }


}
