package com.nastya.bookShop.service.api;

import com.nastya.bookShop.model.user.UserDto;

public interface UserService {

    UserDto findByUserName(String username);

    Boolean existsByUserName(String username);

    Boolean existsByEmail(String email);

    void saveUser(UserDto userDto);

}
