package net.greenbone.demolibrary.bdd.steps;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import net.greenbone.demolibrary.bdd.helper.adapter.context.UserContext;
import net.greenbone.demolibrary.bdd.helper.adapter.http.client.UserClient;
import net.greenbone.demolibrary.representations.response.UserResponse;
import org.hamcrest.Matchers;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

@RequiredArgsConstructor
public class ReadUserSteps {

    private final UserContext userContext;
    private UserResponse userResponse;

    @When("user tries to read a specified user's information")
    public void userTriesToReadASpecifiedUserSInformation() {
        userResponse = userContext.getFeignClient(UserClient.class).getUserById(4L);
    }

    @Then("the information to the specified user is shown")
    public void theInformationToTheSpecifiedUserIsShown() {
        assertThat(4L, Matchers.is(userResponse.getId()));
    }

    @When("user tries to read a non-existing user information")
    public void userTriesToReadANonExistingUserInformation() {
        try{
            userResponse = userContext.getFeignClient(UserClient.class).getUserById(1L);
        }catch(Exception e){
            userContext.setResponse(e);
        }
    }
}
