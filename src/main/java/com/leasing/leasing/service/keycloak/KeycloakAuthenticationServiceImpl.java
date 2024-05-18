package com.leasing.leasing.service.keycloak;

import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.leasing.leasing.DTO.SignInDTO;
import com.leasing.leasing.DTO.SignOutDTO;

@Service
public class KeycloakAuthenticationServiceImpl implements KeycloakAuthenticationService{

    @Value("http://localhost:8080")
    private String serverUrl;
    @Value("project")
    private String realm;
    @Value("project_realm")
    private String clientId;
    @Value("9QvhxhJEEXfEMKnQq5Gixkq4uhvy8IWT")
    private String clientSecret;
    @Value("password")
    private String grantType;

    @Override
    public AccessTokenResponse signIn(SignInDTO signInDTO) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("username", signInDTO.username());
        params.add("password", signInDTO.password());
        params.add("grant_type", grantType);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, getHttpHeaders());
        
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.exchange(
            getRequestUrl("token"),
            HttpMethod.POST,
            requestEntity,
            AccessTokenResponse.class
        ).getBody();
    }

    @Override
    public void signOut(SignOutDTO signOutDTO) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("refresh_token", signOutDTO.refreshToken());
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, getHttpHeaders());

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(getRequestUrl("logout"), requestEntity, Object.class);
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        return headers;
    }

    private String getRequestUrl(String action) {
        return UriComponentsBuilder.fromHttpUrl(serverUrl)
                .pathSegment("realms")
                .pathSegment(realm)
                .pathSegment("protocol")
                .pathSegment("openid-connect")
                .pathSegment(action)
                .toUriString();
    }
}
