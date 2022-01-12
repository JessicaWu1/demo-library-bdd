package net.greenbone.demolibrary.adapter.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.dao.EmptyResultDataAccessException;
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
import java.util.NoSuchElementException;

@RestControllerAdvice
@Order(1)
@Slf4j
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {
    //Each Exception Handled separately -> write a generalExceptionHandlerClass
    //exceptions, die bis zum controller gereicht werden, werden hier behandelt -> try catch aus den services raus und hier die überprüfungen auch
    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException entityNotFoundException){
        Map<String, String> message = Collections.singletonMap("response", entityNotFoundException.getMessage());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NoSuchElementException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException noSuchElementException){
        Map<String, String> message = Collections.singletonMap("response", noSuchElementException.getMessage());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException emptyResultDataAccessException){
        Map<String, String> message = Collections.singletonMap("response", emptyResultDataAccessException.getMessage());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

/*    @ExceptionHandler({NullPointerException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException nullPointerException){
        Map<String, String> message = Collections.singletonMap("response", nullPointerException.getMessage());
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException accessDeniedException){
        Map<String, String> message = Collections.singletonMap("response", accessDeniedException.getMessage());
        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Map<String,String>> handleAuthenticationException(AuthenticationException authenticationException){
        Map<String, String> message = Collections.singletonMap("response", authenticationException.getMessage());
        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }
}
