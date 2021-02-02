package com.nastya.bookShop.controller;

import com.nastya.bookShop.model.Order.OrderContentDto;
import com.nastya.bookShop.model.Order.OrderDto;
import com.nastya.bookShop.model.shop.ShopDto;
import com.nastya.bookShop.service.api.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    public final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        try {
            return new ResponseEntity<>(orderService.saveOrder(orderDto), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Order error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/content/create")
    public ResponseEntity<OrderContentDto> createOrderContent(@RequestBody OrderContentDto orderContentDto) {
        try {
            return new ResponseEntity<>(orderService.saveOrderContent(orderContentDto), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Order error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/shop/{id}")
    public ResponseEntity<List<OrderDto>> getOrdersByShop(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(orderService.getOrderByShop(id), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Order error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(orderService.getOrder(id), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Order error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/content/{orderId}")
    public ResponseEntity<List<OrderContentDto>> getOrderContent(@PathVariable("orderId") Integer orderId) {
        try {
            return new ResponseEntity<>(orderService.getOrderContent(orderId), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Order error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
