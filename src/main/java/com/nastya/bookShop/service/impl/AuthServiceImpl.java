package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.model.request.LoginRequest;
import com.nastya.bookShop.model.request.SignUpRequest;
import com.nastya.bookShop.model.response.JwtResponse;
import com.nastya.bookShop.model.response.MessageResponse;
import com.nastya.bookShop.model.role.ERole;
import com.nastya.bookShop.model.role.RoleDto;
import com.nastya.bookShop.model.user.ConfirmationTokenDto;
import com.nastya.bookShop.model.user.UserDto;
import com.nastya.bookShop.model.userRoles.UserRolesDto;
import com.nastya.bookShop.security.jwt.JwtUtils;
import com.nastya.bookShop.security.services.UserDetailsImpl;
import com.nastya.bookShop.service.api.AuthService;
import com.nastya.bookShop.service.api.ConfirmationTokenService;
import com.nastya.bookShop.service.api.UserRoleService;
import com.nastya.bookShop.service.api.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
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
    private ConfirmationTokenService confirmationTokenService;
    private JavaMailSender mailSender;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, PasswordEncoder encoder,
                           JwtUtils jwtUtils, UserService userService, UserRoleService userRoleService,
                           ConfirmationTokenService confirmationTokenService,
                           JavaMailSender mailSender) {
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.confirmationTokenService = confirmationTokenService;
        this.mailSender = mailSender;
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
        jwtResponse.setIsEnabled(userDetails.isEnabled());
        return ResponseEntity.ok(jwtResponse);
    }

    @Override
    public ResponseEntity<?> registerUser(SignUpRequest signUpRequest)
            throws UnsupportedEncodingException, MessagingException {
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
        return createUser(signUpRequest);
    }

    private ResponseEntity createUser(SignUpRequest signUpRequest)
            throws UnsupportedEncodingException, MessagingException {

        UserDto user = new UserDto();
        ConfirmationTokenDto confirmationToken = new ConfirmationTokenDto();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        Boolean activated = signUpRequest.getActivated();
        if (activated == null) {
            user.setActivated(true);
        } else {
            user.setActivated(signUpRequest.getActivated());
        }
        String randomCode = RandomString.make(64);
        confirmationToken.setConfirmationToken(randomCode);
        confirmationToken.setUsername(user.getUsername());
        user.setIsEnabled(false);
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
        confirmationTokenService.save(confirmationToken);
        sendVerificationEmail(user, confirmationToken);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    private void sendVerificationEmail(UserDto user, ConfirmationTokenDto confirmationToken)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "levkovich.astya@gmail.com";
        String senderName = "Nastya";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Your Bookshop.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getFirstName() + " " + user.getLastName());
        String verifyURL = "localhost:4200/auth/" + confirmationToken.getConfirmationToken();
        content = content.replace("[[URL]]", verifyURL);
        helper.setText(content, true);
        mailSender.send(message);
    }

    public ResponseEntity verify(String verificationCode) {
        ConfirmationTokenDto confirmationTokenDto = confirmationTokenService.findByToken(verificationCode);
        UserDto user = userService.findByUserName(confirmationTokenDto.getUsername());
        if (user == null || user.getIsEnabled()) {
            return (ResponseEntity) ResponseEntity.badRequest();
        } else {
            confirmationTokenDto.setConfirmationToken(null);
            user.setIsEnabled(true);
            userService.saveUser(user);
            confirmationTokenService.save(confirmationTokenDto);
            return ResponseEntity.ok(new MessageResponse("User verified successfully!"));
        }
    }
}
