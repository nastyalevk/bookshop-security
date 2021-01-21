package com.nastya.bookShop.model.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Integer id;
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

}
