package com.nastya.bookShop.controller;

import com.nastya.bookShop.dto.BookDto;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
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
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        ResponseEntity<List<BookDto>> response = restTemplate.exchange("http://localhost:8080/books", HttpMethod.GET, null, new ParameterizedTypeReference<List<BookDto>>() {
        });
        return response;
    }

//    @GetMapping("/books")
//    public ResponseEntity getBooks() {
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
//        RestTemplate restTemplate = new RestTemplate(requestFactory);
//        ResponseEntity<List<BookDto>> response = restTemplate.exchange("http://localhost:8080/books", HttpMethod.GET, null, new ParameterizedTypeReference<List<BookDto>>() {
//        });
//        return response;
//    }

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
