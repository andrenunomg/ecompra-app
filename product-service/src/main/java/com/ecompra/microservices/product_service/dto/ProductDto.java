package com.ecompra.microservices.product_service.dto;

import com.ecompra.microservices.product_service.model.Category;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductDto(String id, String name, String description, BigDecimal price, Category category) {
}
