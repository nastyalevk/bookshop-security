package com.nastya.bookShop.service.api;

public interface AssortmentService {

    int getPrice(int id);

    int getPriceByBookShop(int bookId, int shopId);

}
