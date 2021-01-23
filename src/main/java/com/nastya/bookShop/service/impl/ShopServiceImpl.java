package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.model.shop.ShopDto;
import com.nastya.bookShop.service.api.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    private final RestTemplate restTemplate;

    @Autowired
    public ShopServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ShopDto> getShopByBook(int id) {
        return restTemplate.getForObject(UrlConst.ShopUrl + "/book/" + id, List.class);
    }

}
