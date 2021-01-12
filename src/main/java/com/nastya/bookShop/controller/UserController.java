package com.nastya.bookShop.controller;

import com.nastya.bookShop.service.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


}
