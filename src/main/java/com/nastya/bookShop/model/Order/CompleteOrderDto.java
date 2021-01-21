package com.nastya.bookShop.model.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Date OrderSubmitDate;
    private Integer classificationId;
    private String classificationStatus;
    private Date orderCompleteDate;
    private Integer userId;

    List<OrderContentDto> orderContent;
}
