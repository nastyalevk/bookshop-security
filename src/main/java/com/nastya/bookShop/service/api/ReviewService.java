package com.nastya.bookShop.service.api;

import com.nastya.bookShop.model.review.BookReviewDto;
import com.nastya.bookShop.model.review.ShopReviewDto;

import java.util.List;

public interface ReviewService {

    List<ShopReviewDto> getShopReview(Integer shopId);

    List<BookReviewDto> getBookReview(Integer bookId);

    ShopReviewDto saveShopReview(ShopReviewDto shopReviewDto);

    BookReviewDto saveBookReview(BookReviewDto bookReviewDto);
}
