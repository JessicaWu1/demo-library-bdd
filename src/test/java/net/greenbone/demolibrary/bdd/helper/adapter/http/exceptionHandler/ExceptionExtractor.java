package net.greenbone.demolibrary.bdd.helper.adapter.http.exceptionHandler;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionExtractor {
    public static RequestException extractRequestException(Throwable e) {
        log.info("Exception Extractor Exception message: " +  e.getMessage());
        if (e instanceof FeignException) {
            FeignException exception = (FeignException) e;
            return new RequestException(exception.getMessage(), exception.status());
        } else if (e instanceof RequestException) {
            RequestException exception = (RequestException) e;
            return new RequestException(exception.getMessage(), exception.getStatus());
        }
        throw new RuntimeException("Invalid exception", e);
    }

}
