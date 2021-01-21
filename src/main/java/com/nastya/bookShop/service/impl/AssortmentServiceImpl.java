package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.model.book.BookDto;
import com.nastya.bookShop.service.api.AssortmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AssortmentServiceImpl implements AssortmentService {

    private static final Logger logger = LoggerFactory.getLogger(AssortmentServiceImpl.class);

    private final RestTemplate restTemplate;

    @Autowired
    public AssortmentServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public int getPrice(int id) {
        try {
            return restTemplate.getForObject(UrlConst.AssortmentUrl + "/price/" + id, int.class);
        } catch (Exception e) {
            logger.error("Book error: {}", e.getMessage());
            throw new RuntimeException(e);
        }    }
}
