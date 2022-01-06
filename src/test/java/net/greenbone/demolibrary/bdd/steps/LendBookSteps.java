package net.greenbone.demolibrary.bdd.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import net.greenbone.demolibrary.bdd.helper.adapter.context.UserContext;
import net.greenbone.demolibrary.bdd.helper.adapter.http.client.LendBookClient;
import net.greenbone.demolibrary.representations.request.LendBookRequest;
import net.greenbone.demolibrary.representations.response.LendBookResponse;
import org.hamcrest.Matchers;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RequiredArgsConstructor
public class LendBookSteps {

    private final UserContext userContext;
    private LendBookRequest lendBookRequest;
    private LendBookResponse lendBookResponse;
    private Date date = new Date();

    @When("user tries to lend an existing book")
    public void userTriesToLendAnExistingBook() {
        lendBookRequest = LendBookRequest.builder()
                .returned(false)
                .returnDate(new Date())
                .userId(1L)
                .bookId(2L)
                .build();
        lendBookResponse = userContext.getFeignClient(LendBookClient.class).lendBook(lendBookRequest,this.userContext.getHeaderMap());
    }

    @Then("user is shown the return date of that book")
    public void userIsShownTheReturnDateOfThatBook() {
        assertEquals(date, Matchers.is(lendBookResponse.getReturnDate()));
    }

    @When("user tries to lend a non-existing book")
    public void userTriesToLendANonExistingBook() {
        try{
            lendBookResponse = userContext.getFeignClient(LendBookClient.class).lendBook(lendBookRequest,this.userContext.getHeaderMap());
        }catch(Exception e){
            userContext.setResponse(e);
        }
    }
}
