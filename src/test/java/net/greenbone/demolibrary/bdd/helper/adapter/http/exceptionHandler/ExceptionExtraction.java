package net.greenbone.demolibrary.bdd.helper.adapter.http.exceptionHandler;

import feign.FeignException;

public class ExceptionExtraction {
    public static RequestException extractRequestException(Throwable e) {
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
