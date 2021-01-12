package com.nastya.bookShop.service.api;

import com.nastya.bookShop.model.user.UserDto;

import java.util.List;

public interface UserService {

    UserDto findByUserName(String username);

    Boolean existsByUserName(String username);

    Boolean existsByEmail(String email);

    void saveUser(UserDto userDto);

    List<UserDto> findAll();

    UserDto getOne(Integer id);

}
