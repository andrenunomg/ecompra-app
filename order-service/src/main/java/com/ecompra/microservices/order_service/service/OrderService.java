package com.ecompra.microservices.order_service.service;

import com.ecompra.microservices.order_service.client.InventoryClient;
import com.ecompra.microservices.order_service.dto.OrderDto;
import com.ecompra.microservices.order_service.event.OrderPlacedEvent;
import com.ecompra.microservices.order_service.mapper.OrderMapper;
import com.ecompra.microservices.order_service.model.Order;
import com.ecompra.microservices.order_service.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    Logger log = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, InventoryClient inventoryClient, InventoryClient inventoryClient1, KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.inventoryClient = inventoryClient1;
        this.kafkaTemplate = kafkaTemplate;
    }

    public OrderDto placeOrder(OrderDto orderRequest) {
        boolean isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        if(isProductInStock) {
            Order order = orderMapper.mapOrderDtoToOrder(orderRequest);
            order.setOrderNumber(UUID.randomUUID().toString());
            //send message in kafka
            log.info("Order placed");
            OrderDto dtoSaved = orderMapper.mapOrderToOrderDto(orderRepository.save(order));
            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(order.getOrderNumber(), orderRequest.email());
            kafkaTemplate.send("order-placed", orderPlacedEvent);
            log.info("Order end");
            return dtoSaved;
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
