package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.model.role.RoleDto;
import com.nastya.bookShop.service.api.RoleService;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(
            HttpClientBuilder.create().build()));

    @Override
    public Optional<RoleDto> findByName(String name) {
        return restTemplate.getForObject(UrlConst.RoleUrl + name, Optional.class);
    }
}
