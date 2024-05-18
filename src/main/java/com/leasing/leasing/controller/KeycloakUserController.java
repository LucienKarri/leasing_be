package com.leasing.leasing.controller;

import org.springframework.web.bind.annotation.RestController;

import com.leasing.leasing.DTO.SignOnDTO;
import com.leasing.leasing.service.keycloak.KeycloakUserService;

import lombok.AllArgsConstructor;

import java.security.Principal;

import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class KeycloakUserController {

    private final KeycloakUserService keycloakUserService;

    @PostMapping
    public SignOnDTO createUser(@RequestBody SignOnDTO signInDTO) {
        return keycloakUserService.createUser(signInDTO);
    }

    @GetMapping
    public UserRepresentation getUser(Principal principal){
        return keycloakUserService.getUserById(principal.getName());
    }
    
    @DeleteMapping("/{userId}")
    public  void deleteUserById(@PathVariable String userId){
        keycloakUserService.deleteUserById(userId);
    }
}
