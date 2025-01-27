package com.ecompra.microservices.order_service.controller;

import com.ecompra.microservices.order_service.dto.OrderDto;
import com.ecompra.microservices.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @ResponseStatus(HttpStatus.CREATED)
    public String getAllOrders(@RequestBody OrderDto orderRequest) {
        //TO DO
        return null;
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String changeOrder(@RequestBody OrderDto orderRequest) {
        //TO DO
        return null;
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteOrder(@RequestBody OrderDto orderRequest) {
        return null;
    }
}
