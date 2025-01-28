package com.ecompra.microservices.order_service.controller;

import com.ecompra.microservices.order_service.dto.OrderDto;
import com.ecompra.microservices.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderDto orderRequest) {
        OrderDto order = orderService.placeOrder(orderRequest);
        return "Order Placed Successfully: " + order.toString();
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<OrderDto> getAllOrders() {
        List<OrderDto> orders = orderService.getAllOrders();
        return orders;
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public OrderDto changeOrder(@RequestBody OrderDto orderRequest) {
        return orderService.changeOrder(orderRequest);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteOrder(@RequestBody OrderDto orderRequest) {
        orderService.deleteOrder(orderRequest.id());
    }
}
