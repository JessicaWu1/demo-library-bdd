package net.greenbone.demolibrary.bdd.steps;

import feign.Feign;
import feign.FeignException;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import net.greenbone.demolibrary.bdd.helper.adapter.context.UserContext;
import net.greenbone.demolibrary.bdd.helper.adapter.http.client.BookClient;
import net.greenbone.demolibrary.representations.response.BookResponse;
import org.hamcrest.Matchers;
import org.junit.Before;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RequiredArgsConstructor
public class ReadBookSteps {

    private final UserContext userContext;

    private BookResponse bookResponse;


    @When("user tries to read book information")
    public void userTriesToReadBookInformation() {
        bookResponse =  userContext.getFeignClient(BookClient.class).getBookById(1L);
    }

    @Then("the information is shown")
    public void theInformationIsShown() {
        assertNotNull(bookResponse);
        assertThat(1L, Matchers.is(bookResponse.getId()));
    }

    @When("user tries to read non-existing book information")
    public void userTriesToReadNonExistingBookInformation() {
        try{
            bookResponse = userContext.getFeignClient(BookClient.class).getBookById(3L);
        }catch(Exception e){
            userContext.setResponse(e);
        }
    }
}
