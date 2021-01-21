package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.model.book.BookDto;
import com.nastya.bookShop.model.response.PageResponse;
import com.nastya.bookShop.service.api.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    private final RestTemplate restTemplate;

    @Autowired
    public BookServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public ResponseEntity<String> save(BookDto bookDto) {
        try {
            return restTemplate.postForEntity(UrlConst.BookUrl + "create", bookDto, String.class);
        } catch (Exception e) {
            logger.error("Book error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity getAllBook(String bookName, int page, int size, String sort) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(UrlConst.BookUrl)
                    .queryParam("bookName", bookName)
                    .queryParam("page", page)
                    .queryParam("size", size)
                    .queryParam("sort", sort);

            HttpEntity<?> entity = new HttpEntity<>(headers);

            HttpEntity request = new HttpEntity(headers);

            return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, PageResponse.class);
        } catch (Exception e) {
            logger.error("Book error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<String> updateBook(BookDto bookDto) {
        try {
            return restTemplate.postForEntity(UrlConst.BookUrl + "update", bookDto, String.class);
        } catch (Exception e) {
            logger.error("Book error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public BookDto getOne(Integer id) {
        try {
            return restTemplate.getForObject(UrlConst.BookUrl + id, BookDto.class);
        } catch (Exception e) {
            logger.error("Book error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
