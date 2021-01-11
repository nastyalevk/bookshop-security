package com.nastya.bookShop.service.api;

import com.nastya.bookShop.model.book.BookDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {

        ResponseEntity<String> save (BookDto bookDto);

        List getAllBook();

        ResponseEntity<String> updateBook(BookDto bookDto);
}
