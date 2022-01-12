package net.greenbone.demolibrary.bdd.helper.adapter.http.exceptionHandler;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import static feign.FeignException.errorStatus;

@Slf4j
public class RequestErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() >= 400 && response.status() <= 599) {
            Response.Body body = response.body();
            String message = "";
            if (body != null) {
                message = body.toString();
            }
            return new RequestException(message, response.status());
        }
        return errorStatus(methodKey, response);
    }
}
