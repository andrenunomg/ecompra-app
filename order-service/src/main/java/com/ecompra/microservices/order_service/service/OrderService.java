package com.ecompra.microservices.order_service.service;

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

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public OrderDto placeOrder(OrderDto orderRequest) {
        Order order = orderMapper.mapOrderDtoToOrder(orderRequest);
        return orderMapper.mapOrderToOrderDto(orderRepository.save(order));
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
