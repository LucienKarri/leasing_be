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

import com.leasing.leasing.DTO.RefreshTokenDTO;
import com.leasing.leasing.DTO.SignInDTO;

@Service
public class KeycloakAuthenticationServiceImpl implements KeycloakAuthenticationService{

    @Value("http://localhost:8080")
    private String serverUrl;
    @Value("project")
    private String realm;
    @Value("project_realm")
    private String clientId;
    @Value("t0My4qmj9Sgtb1INRtkFT7xp1WtEn6LC")
    private String clientSecret;
    @Value("password")
    private String grantTypePassword;
    @Value("refresh_token")
    private String grantTypeRefresh;

    @Override
    public AccessTokenResponse signIn(SignInDTO signInDTO) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("username", signInDTO.username());
        params.add("password", signInDTO.password());
        params.add("grant_type", grantTypePassword);
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
    public void signOut(RefreshTokenDTO refreshTokenDTO) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("refresh_token", refreshTokenDTO.refreshToken());
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, getHttpHeaders());

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(getRequestUrl("logout"), requestEntity, Object.class);
    }

    @Override
    public AccessTokenResponse refresh(RefreshTokenDTO refreshTokenDTO) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("refresh_token", refreshTokenDTO.refreshToken());
        params.add("grant_type", grantTypeRefresh);
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
