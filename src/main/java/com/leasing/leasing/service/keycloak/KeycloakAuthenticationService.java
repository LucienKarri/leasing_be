package com.leasing.leasing.service.keycloak;

import org.keycloak.representations.AccessTokenResponse;

import com.leasing.leasing.DTO.SignInDTO;
import com.leasing.leasing.DTO.SignOutDTO;

public interface KeycloakAuthenticationService {
    AccessTokenResponse signIn(SignInDTO signInDTO);

    void signOut(SignOutDTO signOutDTO);
}
