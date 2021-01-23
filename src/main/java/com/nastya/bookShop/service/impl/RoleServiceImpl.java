package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.model.role.RoleDto;
import com.nastya.bookShop.service.api.RoleService;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private final RestTemplate restTemplate;

    @Autowired
    public RoleServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<RoleDto> findByName(String name) {
        CloseableHttpClient httpClientBuilder = HttpClientBuilder.create().build();
        return restTemplate.getForObject(UrlConst.RoleUrl + name, Optional.class);
    }
}
