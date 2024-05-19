package com.leasing.leasing.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Beans {

    @Value("admin-cli")
    private String adminCli;
    @Value("NdQ0XJiB2MOhtd3P52t97A5BfLa6mSjJ")
    private String adminCliSecret;
    @Value("project")
    private String realm;
    @Value("http://localhost:8080")
    private String serverUrl;


    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                            .serverUrl(serverUrl)
                            .realm(realm)
                            .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                            .clientId(adminCli)
                            .clientSecret(adminCliSecret)
                            .build();
    }
}
