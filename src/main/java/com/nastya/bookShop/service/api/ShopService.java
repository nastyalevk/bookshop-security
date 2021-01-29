package com.nastya.bookShop.service.api;

import com.nastya.bookShop.model.shop.ShopDto;

import java.util.List;

public interface ShopService {
    List<ShopDto> getShopByBook(int bookId);

    List<ShopDto> getShopByUsername(String username);
}
