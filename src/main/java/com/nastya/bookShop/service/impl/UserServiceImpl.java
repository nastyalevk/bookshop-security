package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.model.role.ERole;
import com.nastya.bookShop.model.role.RoleDto;
import com.nastya.bookShop.model.user.FEUser;
import com.nastya.bookShop.model.user.UserDto;
import com.nastya.bookShop.service.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Override
    public void updateUser(UserDto userDto) {
        try {
            restTemplate.postForEntity(UrlConst.UserUrl + "update/", userDto, String.class);
        } catch (Exception e) {
            logger.error("User error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private UserDto transfer(FEUser user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setEmail(user.getEmail());
        userDto.setLastName(user.getLastName());
        userDto.setPassword(user.getPassword());
        userDto.setActivated(user.getActivated());
        List<String> roles = user.getRoles();
        RoleDto roleDto = new RoleDto();
        Set<RoleDto> userDtoroles = new HashSet<>();
        for (String i : roles) {
            switch (i) {
                case "ROLE_ADMIN":
                    roleDto.setId(1);
                    roleDto.setName(ERole.ROLE_ADMIN);
                    userDtoroles.add(roleDto);
                    break;
                case "ROLE_OWNER":
                    roleDto.setId(2);
                    roleDto.setName(ERole.ROLE_OWNER);
                    userDtoroles.add(roleDto);
                    break;
                case "ROLE_CLIENT":
                    roleDto.setId(3);
                    roleDto.setName(ERole.ROLE_CLIENT);
                    userDtoroles.add(roleDto);
                    break;
            }
        }
        userDto.setRoles(userDtoroles);
        return userDto;
    }

    private FEUser transfer(UserDto userDto) {
        FEUser user = new FEUser();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setEmail(userDto.getEmail());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setActivated(userDto.getActivated());
        List<String> roles = new ArrayList<>();
        Set<RoleDto> userRoles = userDto.getRoles();
        for (RoleDto i : userRoles) {
            roles.add(i.getName().toString());
        }
        return user;
    }

}
