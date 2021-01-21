package com.nastya.bookShop.service.api;

import com.nastya.bookShop.model.Order.CompleteOrderDto;

public interface OrderService {
    CompleteOrderDto getClientOrder(Integer id);
}
