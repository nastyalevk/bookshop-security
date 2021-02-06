package com.nastya.bookShop.controller;

import com.nastya.bookShop.model.Assortment.AssortmentDto;
import com.nastya.bookShop.service.api.AssortmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assortment")
public class AssortmentController {

    public final AssortmentService assortmentService;

    @Autowired
    public AssortmentController(AssortmentService assortmentService) {
        this.assortmentService = assortmentService;
    }

    @GetMapping("/price/{id}")
    public ResponseEntity<Integer> getMinPrice(@PathVariable("id") Integer bookId) {
        return new ResponseEntity<>(assortmentService.getMinPrice(bookId), HttpStatus.OK);
    }

    @GetMapping("/price/{bookId}/{shopId}")
    public ResponseEntity<Integer> getPriceByBookShop(@PathVariable("bookId") Integer bookId,
                                                      @PathVariable("shopId") Integer shopId) {
        return new ResponseEntity<>(assortmentService.getPriceByBookShop(bookId, shopId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody AssortmentDto assortmentDto) {
        return new ResponseEntity<>(assortmentService.save(assortmentDto), HttpStatus.OK);
    }

    @GetMapping("/exists/{bookId}/{shopId}")
    public ResponseEntity<Boolean> existsByBook(@PathVariable("bookId") Integer bookId,
                                                @PathVariable("shopId") Integer shopId) {
        return new ResponseEntity<>(assortmentService.existsByBook(bookId, shopId), HttpStatus.OK);
    }

    @GetMapping(path = "/delete/{bookId}/{shopId}")
    public ResponseEntity deleteAssortment(@PathVariable("bookId") Integer bookId,
                                           @PathVariable("shopId") Integer shopId) {
        assortmentService.delete(bookId, shopId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/{bookId}/{shopId}")
    public ResponseEntity getOne(@PathVariable("bookId") Integer bookId,
                                 @PathVariable("shopId") Integer shopId) {
        return new ResponseEntity<>(assortmentService.getOne(bookId, shopId), HttpStatus.OK);
    }
}
