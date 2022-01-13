package net.greenbone.demolibrary.bdd.steps;

import io.cucumber.java.en.Then;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.greenbone.demolibrary.bdd.helper.adapter.context.UserContext;
import org.springframework.beans.factory.annotation.Required;

import static org.junit.Assert.assertEquals;

@Slf4j
@RequiredArgsConstructor
public class ResultVerification {

    private final UserContext userContext;

    @Then("the user gets a Bad Request message")
    public void theUserGetsABadRequestException() {
        assertEquals(400, userContext.getResponseStatusCode().intValue());
    }

    @Then("a success message is received")
    public void aSuccessMessageIsShown() {
        assertEquals(200, userContext.getResponseStatusCode().intValue());
    }

    @Then("the user gets a Not Found message")
    public void theUserGetsANotFoundException() {
        assertEquals(404, userContext.getResponseStatusCode().intValue());
    }
}
