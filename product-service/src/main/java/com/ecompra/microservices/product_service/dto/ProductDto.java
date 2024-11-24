package com.ecompra.microservices.product_service.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductDto(String id, String name, String description, BigDecimal price) {
}
