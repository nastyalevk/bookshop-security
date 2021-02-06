package com.nastya.bookShop.model.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompleteOrderDto {

    private Integer orderNumber;
    private Integer shopId;
    private Integer cost;
    private String deliveryAddress;
    private String description;
    private String OrderSubmitDate;
    private OrderClassification classification;
    private String orderCompleteDate;
    private String username;

    List<OrderContentDto> orderContent;
}
