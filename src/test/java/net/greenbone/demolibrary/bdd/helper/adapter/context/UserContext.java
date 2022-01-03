package net.greenbone.demolibrary.bdd.helper.adapter.context;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.greenbone.demolibrary.bdd.helper.adapter.http.exceptionHandler.ExceptionExtractor;
import net.greenbone.demolibrary.bdd.helper.adapter.http.exceptionHandler.RequestException;

@Slf4j
public class UserContext {
    @Getter
    private Integer responseStatusCode;

    private String responseMessage;

    @Setter
    private String email;
    @Setter
    private String password;

    private String baseUrl = "http://localhost:8081";

    public UserContext(){
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
