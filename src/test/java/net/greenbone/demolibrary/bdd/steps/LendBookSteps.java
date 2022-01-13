package net.greenbone.demolibrary.bdd.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.greenbone.demolibrary.bdd.helper.adapter.context.UserContext;
import net.greenbone.demolibrary.bdd.helper.adapter.http.client.LendBookClient;
import net.greenbone.demolibrary.representations.request.LendBookRequest;
import net.greenbone.demolibrary.representations.response.LendBookResponse;
import org.apache.http.client.utils.DateUtils;
import org.hamcrest.Matchers;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RequiredArgsConstructor
@Slf4j
public class LendBookSteps {

    private final UserContext userContext;
    private LendBookRequest lendBookRequest;
    private LendBookResponse lendBookResponse;
    private LocalDate date = setDate();

    private LocalDate setDate() {
        LocalDate returnDate = LocalDate.now();
        returnDate = returnDate.plusDays(14);
        return returnDate;
    }


    @When("user borrows an existing book")
    public void userTriesToLendAnExistingBook() {

        lendBookRequest = LendBookRequest.builder()
                .returned(false)
                .returnDateIn(14)
                .userId(4L)
                .bookId(8L)
                .build();
        log.info("LendBook: " + lendBookRequest.getBookId() + " " + lendBookRequest.getUserId() + " " + lendBookRequest.getReturnDateIn() + " " + lendBookRequest.getReturned());
        lendBookResponse = userContext.getFeignClient(LendBookClient.class).lendBook(lendBookRequest);
    }

    @Then("the relevant information to the borrowed book is returned")
    public void userIsShownTheReturnDateOfThatBook() {
        assertTrue(date.isEqual(lendBookResponse.getReturnDate()));
    }

    @When("user tries to lend a non-existing book")
    public void userTriesToLendANonExistingBook() {
        try{
            lendBookResponse = userContext.getFeignClient(LendBookClient.class).lendBook(lendBookRequest);
        }catch(Exception e){
            userContext.setResponse(e);
        }
    }
}
