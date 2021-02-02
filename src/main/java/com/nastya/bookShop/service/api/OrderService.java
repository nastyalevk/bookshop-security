package com.nastya.bookShop.service.api;

import com.nastya.bookShop.model.Order.CompleteOrderDto;
import com.nastya.bookShop.model.Order.OrderContentDto;
import com.nastya.bookShop.model.Order.OrderDto;

import java.util.List;

public interface OrderService {
    CompleteOrderDto getClientOrder(Integer id);

    OrderDto saveOrder(OrderDto orderDto);

    OrderContentDto saveOrderContent(OrderContentDto orderContentDto);

    List<OrderDto> getOrdersByClientUsername(String username);

    List<OrderDto> getOrderByShop(Integer id);

    OrderDto getOrder(Integer id);

    List<OrderContentDto> getOrderContent(Integer orderId);
}
