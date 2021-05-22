package com.music.suffer.user.controller;

import com.music.suffer.user.model.AuthenticationToken;
import com.music.suffer.user.model.LoginRequest;
import com.music.suffer.user.model.AuthenticationResponse;
import com.music.suffer.user.model.RegistrationRequest;
import com.music.suffer.user.model.User;
import com.music.suffer.user.service.TokenGenerator;
import com.music.suffer.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final TokenGenerator tokenGenerator;
    private final UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthenticationToken> login(@RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken usernameToken = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        );
        Authentication authentication = authenticationManager.authenticate(usernameToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok(tokenGenerator.generate(authentication));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User register(@RequestBody @Valid RegistrationRequest request) {
        return userService.register(request);
    }
}
