package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.model.user.UserDto;
import com.nastya.bookShop.service.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserRoleServiceImpl.class);

    private final RestTemplate restTemplate;

    @Autowired
    public UserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserDto findByUserName(String userName) {
        try {
            return restTemplate.getForObject(UrlConst.UserUrl + "find/username/" + userName, UserDto.class);
        } catch (Exception e) {
            logger.error("User error: {}", e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public Boolean existsByUserName(String userName) {
        try {
            return restTemplate.getForObject(UrlConst.UserUrl + "exist/username/" + userName, Boolean.class);
        } catch (Exception e) {
            logger.error("User error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean existsByEmail(String email) {
        try {
            return restTemplate.getForObject(UrlConst.UserUrl + "exist/email/" + email, Boolean.class);
        } catch (Exception e) {
            logger.error("User error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(UserDto userDto) {
        try {
            restTemplate.postForEntity(UrlConst.UserUrl + "create/", userDto, String.class);
        } catch (Exception e) {
            logger.error("User error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserDto> findAll() {
        try {
            return restTemplate.getForObject(UrlConst.UserUrl, List.class);
        } catch (Exception e) {
            logger.error("User error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDto getOne(Integer id) {
        try {
            return restTemplate.getForObject(UrlConst.UserUrl + id, UserDto.class);
        } catch (Exception e) {
            logger.error("User error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
