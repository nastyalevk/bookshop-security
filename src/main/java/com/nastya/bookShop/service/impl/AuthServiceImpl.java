package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.model.request.LoginRequest;
import com.nastya.bookShop.model.request.SignUpRequest;
import com.nastya.bookShop.model.response.JwtResponse;
import com.nastya.bookShop.model.response.MessageResponse;
import com.nastya.bookShop.model.role.ERole;
import com.nastya.bookShop.model.role.RoleDto;
import com.nastya.bookShop.model.user.UserDto;
import com.nastya.bookShop.model.userRoles.UserRolesDto;
import com.nastya.bookShop.security.jwt.JwtUtils;
import com.nastya.bookShop.security.services.UserDetailsImpl;
import com.nastya.bookShop.service.api.AuthService;
import com.nastya.bookShop.service.api.UserRoleService;
import com.nastya.bookShop.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private UserService userService;
    private UserRoleService userRoleService;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, PasswordEncoder encoder,
                           JwtUtils jwtUtils, UserService userService, UserRoleService userRoleService) {
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @Override
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setToken(jwt);
        jwtResponse.setUsername(userDetails.getUsername());
        jwtResponse.setEmail(userDetails.getEmail());
        jwtResponse.setRoles(roles);
        jwtResponse.setActivated(userDetails.isAccountNonLocked());
        return ResponseEntity.ok(jwtResponse);
    }

    @Override
    public ResponseEntity<?> registerUser(SignUpRequest signUpRequest) {
        if (userService.existsByUserName(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        return newUser(signUpRequest);
    }

    private ResponseEntity newUser(SignUpRequest signUpRequest) {

        UserDto user = new UserDto();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        Boolean activated = signUpRequest.getActivated();
        if (!activated) {
            user.setActivated(true);
        } else {
            user.setActivated(signUpRequest.getActivated());
        }
        Set<String> strRoles = signUpRequest.getRole();
        Set<RoleDto> roles = new HashSet<>();
        if (strRoles == null) {
            RoleDto userRole = new RoleDto();
            userRole.setName(ERole.ROLE_CLIENT);
            userRole.setId(3);
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                RoleDto userRole = new RoleDto();
                switch (role) {
                    case "ROLE_ADMIN":
                        userRole.setName(ERole.ROLE_ADMIN);
                        userRole.setId(1);
                        roles.add(userRole);
                        break;
                    case "ROLE_OWNER":
                        userRole.setName(ERole.ROLE_OWNER);
                        userRole.setId(2);
                        roles.add(userRole);
                        break;
                    case "ROLE_CLIENT":
                        userRole = new RoleDto();
                        userRole.setId(3);
                        userRole.setName(ERole.ROLE_CLIENT);
                        roles.add(userRole);
                        break;
                }
            });
        }
        userService.saveUser(user);
        UserRolesDto userRolesDto = new UserRolesDto();
        for (RoleDto i : roles) {
            userRolesDto.setUsername(user.getUsername());
            userRolesDto.setRoleId(i.getId());
            userRoleService.save(userRolesDto);
        }
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}
