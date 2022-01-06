package net.greenbone.demolibrary.bdd.steps;

import feign.Feign;
import feign.FeignException;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import net.greenbone.demolibrary.bdd.helper.adapter.context.UserContext;
import net.greenbone.demolibrary.bdd.helper.adapter.http.client.BookClient;
import net.greenbone.demolibrary.representations.request.UserRequest;
import net.greenbone.demolibrary.representations.response.BookResponse;
import org.hamcrest.Matchers;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

@RequiredArgsConstructor
public class DeleteBookSteps {

    private final UserContext userContext;
    private BookResponse bookResponse;
    private Map<String,String> message;

    @When("user tries to delete an existing book")
    public void userTriesToDeleteAnExistingBook() {
        userContext.setResponseStatusCode(userContext.getFeignClient(BookClient.class)
                .deleteBook(7L,this.userContext.getHeaderMap())
                .status());
    }

    @When("user tries to delete a non-existing book")
    public void userTriesToDeleteANonExistingBook() {
        try{
            userContext.setResponseStatusCode(
                    userContext.getFeignClient(BookClient.class)
                            .deleteBook(3L,this.userContext.getHeaderMap())
                            .status());
        }catch(Exception e){
            userContext.setResponse(e);
        }
    }
}
