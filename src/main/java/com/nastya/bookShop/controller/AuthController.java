package com.nastya.bookShop.controller;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.model.request.LoginRequest;
import com.nastya.bookShop.model.request.SignUpRequest;
import com.nastya.bookShop.service.api.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(UrlConst.AuthUrl)
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            return authService.authenticateUser(loginRequest);
        } catch (Exception e) {
            logger.error("Authorization error: {}", e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @PostMapping(path = "/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        try {
            return authService.registerUser(signUpRequest);
        } catch (Exception e) {
            logger.error("Registration error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
