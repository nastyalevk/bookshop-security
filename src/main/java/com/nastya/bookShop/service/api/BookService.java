package com.nastya.bookShop.service.api;

import com.nastya.bookShop.model.book.BookDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {

    BookDto save(BookDto bookDto);

    ResponseEntity getAllBook(String bookName, int page, int size, String[] sort);

    ResponseEntity<String> updateBook(BookDto bookDto);

    BookDto getOne(Integer id);

    List<BookDto> getBooksByShop(Integer shopId);
}
