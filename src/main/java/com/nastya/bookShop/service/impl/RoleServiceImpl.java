package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.model.role.RoleDto;
import com.nastya.bookShop.service.api.RoleService;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    private final RestTemplate restTemplate;

    @Autowired
    public RoleServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<RoleDto> findByName(String name) {
        try {
            CloseableHttpClient httpClientBuilder = HttpClientBuilder.create().build();
            return restTemplate.getForObject(UrlConst.RoleUrl + name, Optional.class);
        } catch (Exception e) {
            logger.error("Role error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
