package com.leasing.leasing.service.keycloak;

import org.keycloak.representations.idm.UserRepresentation;

import com.leasing.leasing.DTO.SignOnDTO;

public interface KeycloakUserService {
    SignOnDTO createUser(SignOnDTO signInDTO);

    UserRepresentation getUserById(String userId);

    void deleteUserById(String userId);
    
    void sentEmailVerification(String userId);
}
