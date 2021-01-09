package com.nastya.bookShop.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String jwt, String username, String email, List<String> roles) {
        this.token = jwt;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

}
