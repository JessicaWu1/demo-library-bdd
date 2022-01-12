package net.greenbone.demolibrary.bdd.helper.adapter.context;

import feign.Feign;
import feign.form.FormEncoder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.jackson.JacksonDecoder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.greenbone.demolibrary.bdd.helper.adapter.http.client.KeycloakClient;
import net.greenbone.demolibrary.bdd.helper.adapter.http.exceptionHandler.ExceptionExtractor;
import net.greenbone.demolibrary.bdd.helper.adapter.http.exceptionHandler.RequestErrorDecoder;
import net.greenbone.demolibrary.bdd.helper.adapter.http.exceptionHandler.RequestException;
import net.greenbone.demolibrary.bdd.helper.representations.KeyCloakLoginRequest;
import net.greenbone.demolibrary.bdd.helper.representations.TokenResponse;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class UserContext {
    @Getter
    private Integer responseStatusCode;

    private String responseMessage;

    private String baseUrl = "http://localhost:8081";
    @Getter
    private String client_id = "demo-library";
    @Getter
    private String client_secret = "";

    @Getter
    private KeycloakClient keycloakClient;
    @Getter
    @Setter
    private TokenResponse tokenResponse;

    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;

    @Getter
    Map<String, Object> headerMap;

    public UserContext(){
        keycloakClient = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new FormEncoder())
                .target(KeycloakClient.class, "http://localhost:8080");
        tokenResponse = new TokenResponse();
        headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
    }



    public <T> T getFeignClient(Class<T> clazz) {
        Feign.Builder encoder = Feign.builder()
                .errorDecoder(new RequestErrorDecoder())
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder());

        if (this.tokenResponse.getAccessToken() != null) {
            this.refreshToken();
            encoder.requestInterceptor(template -> template.header(
                            "Authorization",
                            "bearer " + this.tokenResponse.getAccessToken()
                    )
            );
        }

        return encoder.target(clazz, baseUrl);
    }


    public void setResponseStatusCode(Integer responseStatusCode) {
        this.responseStatusCode = responseStatusCode;
        this.responseMessage = null;
    }

    public void setResponse(Throwable e){
        RequestException requestException = ExceptionExtractor.extractRequestException(e);
        log.info("RequestException: "+requestException);
        log.info("Statuscode: "+requestException.getStatus());
        this.responseStatusCode = requestException.getStatus();
        this.responseMessage = requestException.getMessage();
    }

    private void refreshToken() {
        long tokenAgeInSeconds = ChronoUnit.SECONDS.between(this.tokenResponse.getCreationTime(), LocalDateTime.now());
        long remainingSessionInSeconds = this.tokenResponse.getExpiresIn() - tokenAgeInSeconds;

        if (remainingSessionInSeconds < 200) {
            log.info("Refreshing token because it is only active for '{}' seconds ", remainingSessionInSeconds);
            try {
                this.tokenResponse = this.keycloakClient
                        .login(new KeyCloakLoginRequest("password", email, password, client_id, client_secret));
                this.setResponseStatusCode(60);
            } catch (Exception e) {
                this.tokenResponse = null;
                this.setResponse(e);
                throw e;
            }
        } else {
            log.info("Refresh token valid. Token is active for '{}' seconds", remainingSessionInSeconds);
        }

    }
}
