package net.greenbone.demolibrary.bdd.steps;

import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import net.greenbone.demolibrary.bdd.helper.adapter.context.UserContext;
import net.greenbone.demolibrary.bdd.helper.adapter.http.client.LendBookClient;
import net.greenbone.demolibrary.representations.request.LendBookRequest;

import java.util.Date;

@RequiredArgsConstructor
public class ReturnBookSteps {
    private final UserContext userContext;

    private LendBookRequest lendBookrequest;
    @When("user tries to return a book")
    public void userTriesToReturnABook() {
        lendBookrequest = LendBookRequest.builder()
                .bookId(1L)
                .userId(2L)
                .returnDate(new Date())
                .returned(true)
                .build();
        userContext.setResponseStatusCode(userContext.getFeignClient(LendBookClient.class).updateLendBook(3L, lendBookrequest).status());
    }
}
