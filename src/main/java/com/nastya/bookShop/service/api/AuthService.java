package com.nastya.bookShop.service.api;

import com.nastya.bookShop.payload.request.LoginRequest;
import com.nastya.bookShop.payload.request.SignUpRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);

    ResponseEntity<?> registerUser(SignUpRequest signUpRequest);

}
