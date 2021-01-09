package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.model.user.UserDto;
import com.nastya.bookShop.payload.response.MessageResponse;
import com.nastya.bookShop.service.api.UserService;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

    private final RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(
            HttpClientBuilder.create().build()));

    @Override
    public UserDto findByUserName(String userName) {
        return restTemplate.getForObject(UrlConst.UserUrl + "/find/username/" + userName, UserDto.class);

    }

    @Override
    public Boolean existsByUserName(String userName) {
        return restTemplate.getForObject(UrlConst.UserUrl + "/exist/username/" + userName, Boolean.class);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return restTemplate.getForObject(UrlConst.UserUrl + "/exist/email/" + email, Boolean.class);
    }

    @Override
    public void saveUser(UserDto userDto) {
        restTemplate.postForEntity(UrlConst.UserUrl + "/create/", userDto, String.class);
    }
}
