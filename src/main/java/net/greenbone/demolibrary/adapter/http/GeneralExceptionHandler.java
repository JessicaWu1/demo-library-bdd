package net.greenbone.demolibrary.adapter.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.Map;

@RestControllerAdvice
@Order(1)
@Slf4j
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {
    //Each Exception Handled separately -> write a generalExceptionHandlerClass
    //exceptions, die bis zum controller gereicht werden, werden hier behandelt -> try catch aus den services raus und hier die überprüfungen auch
    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String,String>> handleEntityNotFoundException(EntityNotFoundException entityNotFoundException){
        Map<String, String> message = Collections.singletonMap("response", entityNotFoundException.getMessage());
        return (ResponseEntity<Map<String, String>>) message;
    }

    @ExceptionHandler({NullPointerException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Map<String,String>> handleNullPointerException(NullPointerException nullPointerException){
        Map<String, String> message = Collections.singletonMap("response", nullPointerException.getMessage());
        return (ResponseEntity<Map<String, String>>) message;
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Map<String,String>> handleAccessDeniedException(AccessDeniedException accessDeniedException){
        Map<String, String> message = Collections.singletonMap("response", accessDeniedException.getMessage());
        return (ResponseEntity<Map<String, String>>) message;
    }

    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Map<String,String>> handleAuthenticationException(AuthenticationException authenticationException){
        Map<String, String> message = Collections.singletonMap("response", authenticationException.getMessage());
        return (ResponseEntity<Map<String, String>>) message;
    }
}
