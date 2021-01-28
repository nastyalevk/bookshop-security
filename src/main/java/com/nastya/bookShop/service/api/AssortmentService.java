package com.nastya.bookShop.service.api;

public interface AssortmentService {

    int getMinPrice(int bookId);

    int getPriceByBookShop(int bookId, int shopId);

}
