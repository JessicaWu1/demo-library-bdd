package net.greenbone.demolibrary.bdd.steps;

import feign.Feign;
import feign.FeignException;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.greenbone.demolibrary.bdd.helper.adapter.http.client.BookClient;
import net.greenbone.demolibrary.representations.request.UserRequest;
import net.greenbone.demolibrary.representations.response.BookResponse;
import org.hamcrest.Matchers;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class DeleteBookSteps {

    private BookResponse bookResponse;
    private Map<String,String> message;

    @When("user tries to delete an existing book")
    public void userTriesToDeleteAnExistingBook() {
        Feign.Builder encoder = Feign.builder() //Feign client, http rest client gson json ein und auslesen
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder());
        BookClient bookClient = encoder.target(BookClient.class, "http://localhost:8081");
        message = bookClient.deleteBook(1L);
    }

    @When("user tries to delete a non-existing book")
    public void userTriesToDeleteANonExistingBook() {
    }

    @Then("a success message is shown")
    public void aSuccessMessageIsShown() {
        assertNotNull(message);
        assertThat(message.get("response"), Matchers.is("Successfully removed Book with ID: 1" ));
    }

    @Then("the user gets a NotFound Exception")
    public void theUserGetsANotFoundException() {
        Feign.Builder encoder = Feign.builder() //Feign client, http rest client gson json ein und auslesen
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder());
        BookClient bookClient = encoder.target(BookClient.class, "http://localhost:8081");
        assertThatThrownBy(() -> bookClient.deleteBook(3L))
                .isInstanceOf(FeignException.class);
    }
}
