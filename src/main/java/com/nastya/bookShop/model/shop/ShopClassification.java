package com.nastya.bookShop.model.shop;

import lombok.Getter;

@Getter
public enum ShopClassification {

    OPEN("open"),
    CLOSED("closed"),
    TERMINATED("terminated");

    private String name;

    ShopClassification(String name) {
        this.name = name;
    }
}
