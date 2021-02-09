package com.nastya.bookShop.controller;

import com.nastya.bookShop.service.api.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/confirm")
public class ConfirmationTokenController {

    public final ConfirmationTokenService confirmationTokenService;

    @Autowired
    public ConfirmationTokenController(ConfirmationTokenService confirmationTokenService) {
        this.confirmationTokenService = confirmationTokenService;
    }



}
