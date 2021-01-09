package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.model.userRoles.UserRolesDto;
import com.nastya.bookShop.payload.response.MessageResponse;
import com.nastya.bookShop.service.api.UserRoleService;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(
            HttpClientBuilder.create().build()));

    @Override
    public void save(UserRolesDto userRolesDto) {
        restTemplate.postForEntity(UrlConst.UserRoleUrl + "/create/", userRolesDto, String.class);
    }
}
