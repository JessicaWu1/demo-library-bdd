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
import net.greenbone.demolibrary.bdd.helper.adapter.http.client.UserClient;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

@RequiredArgsConstructor
public class DeleteUserSteps {
    private final UserContext userContext;

    @When("user tries to delete another user")
    public void userTriesToDeleteAnotherUser() {
        userContext.getFeignClient(UserClient.class).deleteUser(1L);
    }

    @When("user tries to delete a non-existing user")
    public void userTriesToDeleteANonExistingUser() {
        assertEquals(userContext.getResponseStatusCode().intValue(), 200);
    }

    @Then("the user receives a Not Found Exception")
    public void theUserReceivesANotFoundException() {
        assertEquals(userContext.getResponseStatusCode().intValue(), 404);
    }
}
