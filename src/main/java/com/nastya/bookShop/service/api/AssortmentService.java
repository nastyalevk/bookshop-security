package com.nastya.bookShop.service.api;

import com.nastya.bookShop.model.Assortment.AssortmentDto;
import org.springframework.http.ResponseEntity;

public interface AssortmentService {

    int getMinPrice(int bookId);

    int getPriceByBookShop(int bookId, int shopId);

    ResponseEntity<AssortmentDto> save(AssortmentDto assortmentDto);

    boolean existsByBook(Integer bookId, Integer shopId);

    void delete(Integer bookId, Integer shopId);

    AssortmentDto getOne(Integer bookId, Integer shopId);
}
