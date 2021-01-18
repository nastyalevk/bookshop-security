package com.nastya.bookShop.service.api;

import com.nastya.bookShop.model.book.BookDto;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;

public interface BookService {

    ResponseEntity<String> save(BookDto bookDto);

    ResponseEntity<LinkedHashMap> getAllBook(String bookName, int page, int size, String[] sort);

    ResponseEntity<String> updateBook(BookDto bookDto);

}
