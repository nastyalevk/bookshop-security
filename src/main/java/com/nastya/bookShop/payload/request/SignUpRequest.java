package com.nastya.bookShop.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
public class SignUpRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    private Set<String> role;

    @NotBlank
    private String password;

    private Boolean activated;

}
