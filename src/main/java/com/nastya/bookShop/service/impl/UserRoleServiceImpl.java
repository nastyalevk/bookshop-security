package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.model.userRoles.UserRolesDto;
import com.nastya.bookShop.service.api.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private static final Logger logger = LoggerFactory.getLogger(UserRoleServiceImpl.class);

    private final RestTemplate restTemplate;

    @Autowired
    public UserRoleServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void save(UserRolesDto userRolesDto) {
        try {
            restTemplate.postForEntity(UrlConst.UserRoleUrl + "create/", userRolesDto, String.class);
        } catch (Exception e) {
            logger.error("User roles error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
