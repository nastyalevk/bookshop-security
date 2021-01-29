package com.nastya.bookShop.service.api;

import com.nastya.bookShop.model.Order.CompleteOrderDto;
import com.nastya.bookShop.model.Order.OrderContentDto;
import com.nastya.bookShop.model.Order.OrderDto;

public interface OrderService {
    CompleteOrderDto getClientOrder(Integer id);

    OrderDto saveOrder(OrderDto orderDto);

    OrderContentDto saveOrderContent(OrderContentDto orderContentDto);
}
