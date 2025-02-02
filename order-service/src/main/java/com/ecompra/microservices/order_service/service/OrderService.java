package com.ecompra.microservices.order_service.service;

import com.ecompra.microservices.order_service.client.InventoryClient;
import com.ecompra.microservices.order_service.dto.OrderDto;
import com.ecompra.microservices.order_service.mapper.OrderMapper;
import com.ecompra.microservices.order_service.model.Order;
import com.ecompra.microservices.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final InventoryClient inventoryClient;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, InventoryClient inventoryClient, InventoryClient inventoryClient1) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.inventoryClient = inventoryClient1;
    }

    public OrderDto placeOrder(OrderDto orderRequest) {
        boolean isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        if(isProductInStock) {
            Order order = orderMapper.mapOrderDtoToOrder(orderRequest);
            return orderMapper.mapOrderToOrderDto(orderRepository.save(order));
        } else {
            throw  new RuntimeException("Product not in stock");
        }
    }

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream().map(orderMapper::mapOrderToOrderDto).collect(Collectors.toList());
    }

    public OrderDto changeOrder(OrderDto orderRequest) {
        if (orderRequest.id() != null) {
            Order order = orderMapper.mapOrderDtoToOrder(orderRequest);
            return orderMapper.mapOrderToOrderDto(orderRepository.save(order));
        }
        return null;
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

}
