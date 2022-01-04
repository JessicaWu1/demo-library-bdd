package net.greenbone.demolibrary.bdd.helper.adapter.http.client;

import feign.Headers;
import feign.RequestLine;
import net.greenbone.demolibrary.bdd.helper.representations.KeyCloakLoginRequest;
import net.greenbone.demolibrary.bdd.helper.representations.TokenResponse;

@Headers("Content-Type: application/x-www-form-urlencoded")
public interface KeycloakClient {
    @RequestLine("POST /auth/realms/demo-library/protocol/openid-connect/token")
    TokenResponse login(KeyCloakLoginRequest keyCloakLoginRequest);
}
