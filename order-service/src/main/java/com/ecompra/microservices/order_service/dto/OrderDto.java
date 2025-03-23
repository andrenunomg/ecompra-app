package com.ecompra.microservices.order_service.dto;

import java.math.BigDecimal;

public record OrderDto(Long id, String orderNumber, String skuCode, BigDecimal price, Integer quantity, String email) {
}
