package com.nastya.bookShop.service.api;

import com.nastya.bookShop.model.Order.CompleteOrderDto;
import com.nastya.bookShop.model.Order.OrderContentDto;
import com.nastya.bookShop.model.Order.OrderDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    CompleteOrderDto getClientOrder(Integer id);

    OrderDto saveOrder(OrderDto orderDto);

    OrderContentDto saveOrderContent(OrderContentDto orderContentDto);

    ResponseEntity getOrdersByClientUsername(int page, int size);

    List<OrderDto> getOrderByShop(Integer id);

    OrderDto getOrder(Integer id);

    List<OrderContentDto> getOrderContent(Integer orderId);

}
