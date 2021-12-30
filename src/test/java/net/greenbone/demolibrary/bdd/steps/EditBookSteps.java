package net.greenbone.demolibrary.bdd.steps;

import feign.Feign;
import feign.FeignException;
import feign.Response;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import net.greenbone.demolibrary.bdd.helper.adapter.http.client.BookClient;
import net.greenbone.demolibrary.representations.request.BookRequest;
import net.greenbone.demolibrary.representations.response.BookResponse;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@Slf4j
public class EditBookSteps {

    private BookRequest bookRequest;
    private Map<String,String> message;
    @Before
    public void setUp(){
    }

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

        Feign.Builder encoder = Feign.builder() //Feign client, http rest client gson json ein und auslesen
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder());
        BookClient bookClient = encoder.target(BookClient.class, "http://localhost:8081");
        message = bookClient.updateBook(1L, bookRequest);
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
    }

    @Then("a success message is received")
    public void aSuccessMessageIsReceived() {
        assertNotNull(message);
        assertThat(message.get("response"), Matchers.is("Successfully updated Book with ID: 1" ));
    }

    @Then("the user gets a Not Found Exception")
    public void theUserGetsANotFoundException() {
        Feign.Builder encoder = Feign.builder() //Feign client, http rest client gson json ein und auslesen
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder());
        BookClient bookClient = encoder.target(BookClient.class, "http://localhost:8081");

        assertThatThrownBy(() -> bookClient.updateBook(3L, bookRequest))
                .isInstanceOf(FeignException.class);
    }
}
