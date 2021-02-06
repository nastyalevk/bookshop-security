package com.nastya.bookShop.controller;

import com.nastya.bookShop.model.review.BookReviewDto;
import com.nastya.bookShop.model.review.ShopReviewDto;
import com.nastya.bookShop.service.api.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    public final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/shop/{id}")
    public ResponseEntity<List<ShopReviewDto>> getShopReview(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(reviewService.getShopReview(id), HttpStatus.OK);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<List<BookReviewDto>> getBookReview(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(reviewService.getBookReview(id), HttpStatus.OK);
    }

    @PostMapping("/shop/create")
    public ResponseEntity<ShopReviewDto> createShopReview(@RequestBody ShopReviewDto reviewDto) {
        return new ResponseEntity<>(reviewService.saveShopReview(reviewDto), HttpStatus.OK);
    }

    @PostMapping("/book/create")
    public ResponseEntity<BookReviewDto> createBookReview(@RequestBody BookReviewDto reviewDto) {
        return new ResponseEntity<>(reviewService.saveBookReview(reviewDto), HttpStatus.OK);
    }
}
