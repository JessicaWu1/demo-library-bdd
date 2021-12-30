package net.greenbone.demolibrary.bdd.steps;

import feign.Feign;
import feign.FeignException;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import net.greenbone.demolibrary.bdd.helper.adapter.http.client.BookClient;
import net.greenbone.demolibrary.representations.request.BookRequest;
import net.greenbone.demolibrary.representations.response.BookResponse;
import org.hamcrest.Matchers;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

@Slf4j
public class CreateBookSteps {

    private BookResponse bookResponse;
    private BookRequest bookRequest;

    @When("user creates a new book with the needed information")
    public void userCreatesANewBookWithTheNeededInformation() {
        bookRequest = BookRequest.builder()
                .title("Greatest Book you'll ever read")
                .author("Newcomer Author")
                .description("Really worth your time.")
                .publisher("New Publisher")
                .publishingYear(2015)
                .quantity(1)
                .build();
        Feign.Builder encoder = Feign.builder() //Feign client, http rest client gson json ein und auslesen
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder());
        BookClient bookClient = encoder.target(BookClient.class, "http://localhost:8081");
        bookResponse =  bookClient.createBook(bookRequest);
    }

    @And("the created book information is shown")
    public void theCreatedBookInformationIsShown() {
        assertNotNull(bookResponse);
        assertThat(bookResponse.getAuthor(), Matchers.is(bookRequest.getAuthor()));
        assertThat(bookResponse.getTitle(), Matchers.is(bookRequest.getTitle()));
        assertThat(bookResponse.getDescription(), Matchers.is(bookRequest.getDescription()));
        assertThat(bookResponse.getPublisher(), Matchers.is(bookRequest.getPublisher()));
        assertThat(bookResponse.getPublishingYear(), Matchers.is(bookRequest.getPublishingYear()));
        assertThat(bookResponse.getQuantity(), Matchers.is(bookRequest.getQuantity()));
    }

    @When("user tries to create a Book with missing information")
    public void userTriesToCreateABookWithMissingInformation() {
        bookRequest = BookRequest.builder()
                .author("Newcomer Author")
                .description("Really worth your time.")
                .publisher("New Publisher")
                .publishingYear(2015)
                .quantity(1)
                .build();
    }

    @Then("the user gets a Bad request Exception")
    public void theUserGetsABadRequestException() {
        Feign.Builder encoder = Feign.builder() //Feign client, http rest client gson json ein und auslesen
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder());
        BookClient bookClient = encoder.target(BookClient.class, "http://localhost:8081");
        assertThatThrownBy(() -> bookClient.createBook(bookRequest))
                .isInstanceOf(FeignException.class);

    }
}
