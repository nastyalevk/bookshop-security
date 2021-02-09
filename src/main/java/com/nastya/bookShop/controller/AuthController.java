package com.nastya.bookShop.controller;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.model.request.LoginRequest;
import com.nastya.bookShop.model.request.SignUpRequest;
import com.nastya.bookShop.service.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping(UrlConst.AuthUrl)
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @PostMapping(path = "/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest)
            throws UnsupportedEncodingException, MessagingException {
        return authService.registerUser(signUpRequest);
    }

    @GetMapping("/verify/")
    public ResponseEntity verifyUser(@RequestParam(name = "code") String code) {
        return authService.verify(code);
    }
}
