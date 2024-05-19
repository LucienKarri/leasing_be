package com.leasing.leasing.service.keycloak;

import org.keycloak.representations.AccessTokenResponse;

import com.leasing.leasing.DTO.RefreshTokenDTO;
import com.leasing.leasing.DTO.SignInDTO;

public interface KeycloakAuthenticationService {
    AccessTokenResponse signIn(SignInDTO signInDTO);

    AccessTokenResponse refresh(RefreshTokenDTO refreshTokenDTO);

    void signOut(RefreshTokenDTO refreshTokenDTO);
}
