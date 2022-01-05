package net.greenbone.demolibrary.bdd.steps;

import feign.Feign;
import feign.FeignException;
import feign.Response;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.greenbone.demolibrary.bdd.helper.adapter.context.UserContext;
import net.greenbone.demolibrary.bdd.helper.adapter.http.client.BookClient;
import net.greenbone.demolibrary.representations.request.BookRequest;
import net.greenbone.demolibrary.representations.response.BookResponse;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

@Slf4j
@RequiredArgsConstructor
public class EditBookSteps {

    private final UserContext userContext;
    private BookRequest bookRequest;

    @When("user edits something in an existing book")
    public void userEditsSomethingInAnExistingBook() {
        bookRequest = BookRequest.builder()
                .title("Greatest Book you'll ever read")
                .author("Newcomer Author")
                .description("Really worth your time.")
                .publisher("New Publisher")
                .publishingYear(2015)
                .quantity(2)
                .build();

        userContext.setResponseStatusCode(userContext.getFeignClient(BookClient.class).updateBook(1L, bookRequest).status());
    }

    @When("user tries to edit a non-existing book")
    public void userTriesToEditANonExistingBook() {
        bookRequest = BookRequest.builder()
                .title("Greatest Book you'll ever read")
                .author("Newcomer Author")
                .description("Really worth your time.")
                .publisher("New Publisher")
                .publishingYear(2015)
                .quantity(2)
                .build();
        try{
            userContext.getFeignClient(BookClient.class).updateBook(3L, bookRequest);
        }catch(Exception e){
            userContext.setResponse(e);
        }
    }
}
