package com.nastya.bookShop.service.api;

import com.nastya.bookShop.model.book.BookDto;
import com.nastya.bookShop.model.book.BooksDto;
import org.springframework.http.ResponseEntity;

public interface BookService {

        ResponseEntity<String> save (BookDto bookDto);

        BooksDto getAllBook();

        ResponseEntity<String> updateBook(BookDto bookDto);
}
