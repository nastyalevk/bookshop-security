package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.model.Order.CompleteOrderDto;
import com.nastya.bookShop.model.Order.OrderContentDto;
import com.nastya.bookShop.model.Order.OrderDto;
import com.nastya.bookShop.service.api.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final RestTemplate restTemplate;

    @Autowired
    public OrderServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public CompleteOrderDto getClientOrder(Integer id){
        try {
            OrderDto order = restTemplate.getForObject(UrlConst.OrderUrl + "client/" + id, OrderDto.class);
            List<OrderContentDto> orderContent = restTemplate.getForObject
                    (UrlConst.OrderContentUrl + order.getId(), List.class);
            CompleteOrderDto completeOrderDto = new CompleteOrderDto();
            completeOrderDto.setOrderNumber(order.getOrderNumber());
            completeOrderDto.setShopId(order.getShopId());
            completeOrderDto.setCost(order.getCost());
            completeOrderDto.setDeliveryAddress(order.getDeliveryAddress());
            completeOrderDto.setDescription(order.getDescription());
            completeOrderDto.setOrderSubmitDate(order.getOrderSubmitDate());
            completeOrderDto.setClassificationId(order.getClassificationId());
            completeOrderDto.setClassificationStatus(order.getClassificationStatus());
            completeOrderDto.setOrderCompleteDate(order.getOrderCompleteDate());
            completeOrderDto.setUserId(order.getUserId());
            completeOrderDto.setOrderContent(orderContent);
            return completeOrderDto;
        }catch (Exception e){
            logger.error("Order error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
