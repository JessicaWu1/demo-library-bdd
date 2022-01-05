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
import net.greenbone.demolibrary.bdd.helper.adapter.http.exceptionHandler.RequestException;
import net.greenbone.demolibrary.bdd.helper.representations.TokenResponse;

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

    public UserContext(){
        keycloakClient = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new FormEncoder())
                .target(KeycloakClient.class, "http://localhost:8080");
        tokenResponse = new TokenResponse();
    }



    public <T> T getFeignClient(Class<T> clazz) {
        Feign.Builder encoder = Feign.builder()
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder());
        return encoder.target(clazz, baseUrl);
    }


    public void setResponseStatusCode(Integer responseStatusCode) {
        this.responseStatusCode = responseStatusCode;
        this.responseMessage = null;
    }

    public void setResponse(Throwable e){
        RequestException requestException = ExceptionExtractor.extractRequestException(e);
        this.responseStatusCode = requestException.getStatus();
        this.responseMessage = requestException.getMessage();
    }

}
