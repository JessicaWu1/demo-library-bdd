package net.greenbone.demolibrary.bdd.steps;

import feign.Feign;
import feign.FeignException;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.greenbone.demolibrary.bdd.helper.adapter.http.client.BookClient;
import net.greenbone.demolibrary.representations.response.BookResponse;
import org.hamcrest.Matchers;
import org.junit.Before;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class ReadBookSteps {

    private BookResponse bookResponse;
    private BookClient bookClient;
    Map<String ,String> message;

    @Before
    public void setUp(){
        Feign.Builder encoder = Feign.builder() //Feign client, http rest client gson json ein und auslesen
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder());
        bookClient = encoder.target(BookClient.class, "http://localhost:8081");
    }

    @When("user tries to read book information")
    public void userTriesToReadBookInformation() {
        bookResponse =  bookClient.getBookById(1L);
    }

    @Then("the information is shown")
    public void theInformationIsShown() {
        assertNotNull(bookResponse);
        assertThat(1L, Matchers.is(bookResponse.getId()));
    }

    @When("user tries to read non-existing book information")
    public void userTriesToReadNonExistingBookInformation() {
    }

    @Then("the user gets a Null Pointer Exception")
    public void theUserGetsANullPointerException() {
        //is this even right??
        assertThatThrownBy(() -> bookClient.getBookById(3L))
                .isInstanceOf(NullPointerException.class);
    }
}
