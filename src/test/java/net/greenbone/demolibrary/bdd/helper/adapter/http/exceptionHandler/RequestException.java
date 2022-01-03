package net.greenbone.demolibrary.bdd.helper.adapter.http.exceptionHandler;

import lombok.Getter;

@Getter
public class RequestException extends RuntimeException{
    private int status;

    public RequestException(String message, int status) {
        super(message);
        this.status = status;
    }
}
