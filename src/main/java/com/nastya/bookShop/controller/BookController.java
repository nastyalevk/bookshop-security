package com.nastya.bookShop.controller;

import com.nastya.bookShop.model.book.BookDto;
import com.nastya.bookShop.service.api.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
    public final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public ResponseEntity findAll(){
        return new ResponseEntity(bookService.getAllBook(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_OWNER')")
    @PostMapping("/create")
    public ResponseEntity create(BookDto bookDto){
        return new ResponseEntity(bookService.save(bookDto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_OWNER')")
    @PostMapping("/update")
    public ResponseEntity update(BookDto bookDto){
        return new ResponseEntity(bookService.updateBook(bookDto), HttpStatus.OK);
    }
}
