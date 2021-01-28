package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.service.api.AssortmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AssortmentServiceImpl implements AssortmentService {

    private final RestTemplate restTemplate;

    @Autowired
    public AssortmentServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public int getMinPrice(int bookId) {
        return restTemplate.getForObject(UrlConst.AssortmentUrl + "/price/" + bookId, int.class);
    }

    @Override
    public int getPriceByBookShop(int bookId, int shopId) {
        return restTemplate.getForObject(UrlConst.AssortmentUrl + "/price/" + bookId + "/" + shopId, int.class);
    }
}
