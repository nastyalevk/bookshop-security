package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.model.Order.CompleteOrderDto;
import com.nastya.bookShop.model.Order.OrderContentDto;
import com.nastya.bookShop.model.Order.OrderDto;
import com.nastya.bookShop.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final RestTemplate restTemplate;

    @Autowired
    public OrderServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public CompleteOrderDto getClientOrder(Integer id) {
        OrderDto order = restTemplate.getForObject(UrlConst.OrderUrl + "client/" + id, OrderDto.class);
        List<OrderContentDto> orderContent = restTemplate.getForObject
                (UrlConst.OrderContentUrl + order.getId(), List.class);
        return transfer(order, orderContent);
    }

    private CompleteOrderDto transfer(OrderDto orderDto, List<OrderContentDto> orderContentDto) {
        CompleteOrderDto completeOrderDto = new CompleteOrderDto();
        completeOrderDto.setOrderNumber(orderDto.getOrderNumber());
        completeOrderDto.setShopId(orderDto.getShopId());
        completeOrderDto.setCost(orderDto.getCost());
        completeOrderDto.setDeliveryAddress(orderDto.getDeliveryAddress());
        completeOrderDto.setDescription(orderDto.getDescription());
        completeOrderDto.setOrderSubmitDate(orderDto.getOrderSubmitDate());
        completeOrderDto.setClassificationId(orderDto.getClassificationId());
        completeOrderDto.setClassificationStatus(orderDto.getClassificationStatus());
        completeOrderDto.setOrderCompleteDate(orderDto.getOrderCompleteDate());
        completeOrderDto.setUserId(orderDto.getUserId());
        completeOrderDto.setOrderContent(orderContentDto);
        return completeOrderDto;
    }

}
