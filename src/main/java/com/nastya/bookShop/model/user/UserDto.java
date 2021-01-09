package com.nastya.bookShop.model.user;

import com.nastya.bookShop.model.role.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Set<RoleDto> roles;

}
