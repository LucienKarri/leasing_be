package com.leasing.leasing.controller;


import org.keycloak.representations.AccessTokenResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leasing.leasing.DTO.RefreshTokenDTO;
import com.leasing.leasing.DTO.SignInDTO;
import com.leasing.leasing.service.keycloak.KeycloakAuthenticationService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class KeycloakAuthenticationController {
    private final KeycloakAuthenticationService keycloakAuthenticationService;

    @PostMapping("/sign-in")
    public AccessTokenResponse signIn(@RequestBody SignInDTO signInDTO) {
        return keycloakAuthenticationService.signIn(signInDTO);
    }

    @PostMapping("/sign-out")
    public void signOut(@RequestBody RefreshTokenDTO refreshTokenDTO) {
        keycloakAuthenticationService.signOut(refreshTokenDTO);
    }

    @PostMapping("/refresh")
    public AccessTokenResponse refresh(@RequestBody RefreshTokenDTO refreshTokenDTO) {
        return keycloakAuthenticationService.refresh(refreshTokenDTO);
    }
}
