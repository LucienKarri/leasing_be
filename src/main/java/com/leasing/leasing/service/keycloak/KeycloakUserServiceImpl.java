package com.leasing.leasing.service.keycloak;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.leasing.leasing.DTO.SignOnDTO;

import jakarta.ws.rs.core.Response;

@Service
public class KeycloakUserServiceImpl implements KeycloakUserService {

    @Value("project")
    private String realm;

    private Keycloak keycloak;

    public KeycloakUserServiceImpl(Keycloak keycloak) {
        this.keycloak = keycloak;
    }


    @Override
    public SignOnDTO createUser(SignOnDTO signInDTO) {
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(signInDTO.username());
        user.setFirstName(signInDTO.firstName());
        user.setLastName(signInDTO.lastName());
        user.setEmail(signInDTO.email());
        user.setEmailVerified(false);

        CredentialRepresentation credentials = new CredentialRepresentation();
        credentials.setTemporary(false);
        credentials.setType(CredentialRepresentation.PASSWORD);
        credentials.setValue(signInDTO.password());

        List<CredentialRepresentation> list = new ArrayList<>();
        list.add(credentials);
        user.setCredentials(list);

        UsersResource usersResource = getUsersResources();

        Response response = usersResource.create(user);

        if(Objects.equals((201), response.getStatus())) {
            List<UserRepresentation> representationList = usersResource.searchByUsername(signInDTO.username(), true);
            if(!CollectionUtils.isEmpty(representationList)) {
                UserRepresentation userRepresentation1 = representationList.stream().filter(userRepresentation->Objects.equals(false, userRepresentation.isEmailVerified())).findFirst().orElse(null);
                sentEmailVerification(userRepresentation1.getId());
            }
            return signInDTO;
        }

        return null;
    }

    private UsersResource getUsersResources(){
        
        RealmResource realm1 = keycloak.realm(realm);
        return realm1.users();
    }

    @Override
    public UserRepresentation getUserById(String userId) {
        return getUsersResources().get(userId).toRepresentation();
    }

    @Override
    public void deleteUserById(String userId) {
        getUsersResources().delete(userId);
    }

    @Override
    public void sentEmailVerification(String userId){
        UsersResource usersResource = getUsersResources();
        usersResource.get(userId).sendVerifyEmail();
    }
}
