package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.model.book.BookDto;
import com.nastya.bookShop.service.api.BookService;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(
            HttpClientBuilder.create().build()));

    @Override
    public ResponseEntity<String> save(BookDto bookDto) {
        return restTemplate.postForEntity(UrlConst.BookUrl + "/create", bookDto, String.class );
    }

    @Override
    public List getAllBook() {
        return restTemplate.getForObject(UrlConst.BookUrl, List.class);
    }

    @Override
    public ResponseEntity<String> updateBook(BookDto bookDto) {
        return restTemplate.postForEntity(UrlConst.BookUrl+"/update", bookDto, String.class);
    }
}
