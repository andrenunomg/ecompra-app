package com.ecompra.microservices.order_service.mapper;


import com.ecompra.microservices.order_service.dto.OrderDto;
import com.ecompra.microservices.order_service.model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Order mapOrderDtoToOrder(OrderDto dto) {
        Order order = new Order();
        order.setOrderNumber(dto.orderNumber());
        order.setPrice(dto.price());
        order.setSkuCode(dto.skuCode());
        order.setQuantity(dto.quantity());
        return order;
    }

    public OrderDto mapOrderToOrderDto(Order order) {
        return new OrderDto(order.getId(), order.getOrderNumber(), order.getSkuCode(), order.getPrice(), order.getQuantity(), null);
    }
}
