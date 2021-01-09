package com.nastya.bookShop.controller;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.model.book.BookDto;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/all")
    public ResponseEntity allAccess() {
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory
                (HttpClientBuilder.create().build()));
        List<BookDto> bookDtos = restTemplate.getForObject(UrlConst.BookUrl, List.class);
        return new ResponseEntity(bookDtos, HttpStatus.OK);
    }

    @GetMapping("/client")
    @PreAuthorize("hasRole('ROLE_CLIENT') or hasRole('ROLE_OWNER') or hasRole('ROLE_ADMIN')")
    public String userAccess() {
        return "Client Content.";
    }

    @GetMapping("/owner")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    public String moderatorAccess() {
        return "Owner Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
