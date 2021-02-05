package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.model.review.BookReviewDto;
import com.nastya.bookShop.model.review.ShopReviewDto;
import com.nastya.bookShop.service.api.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final RestTemplate restTemplate;

    @Autowired
    public ReviewServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ShopReviewDto> getShopReview(Integer shopId) {
        return restTemplate.getForObject(UrlConst.ReviewUrl + "shop/" + shopId, List.class);
    }

    @Override
    public List<BookReviewDto> getBookReview(Integer bookId) {
        return restTemplate.getForObject(UrlConst.ReviewUrl + "book/" + bookId, List.class);
    }

    @Override
    public ShopReviewDto saveShopReview(ShopReviewDto shopReviewDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            shopReviewDto.setUsername(username);
        }
        return this.restTemplate.
                postForEntity(UrlConst.ReviewUrl + "shop/create/", shopReviewDto, ShopReviewDto.class).getBody();
    }

    @Override
    public BookReviewDto saveBookReview(BookReviewDto bookReviewDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            bookReviewDto.setUsername(username);
        }
        return this.restTemplate.
                postForEntity(UrlConst.ReviewUrl + "book/create/", bookReviewDto, BookReviewDto.class).getBody();
    }
}
