package net.greenbone.demolibrary.bdd.steps;

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

    @When("user reads their information")
    public void userReadsTheirInformation() {
        userResponse = userContext.getFeignClient(UserClient.class).getUserById(4L);
    }

    @Then("the information of the user is returned")
    public void theInformationOfTheUserIsReturned() {
        assertThat(4L, Matchers.is(userResponse.getId()));
    }

    @When("admin reads a non-existing user information")
    public void adminReadsANonExistingUserInformation() {
        try{
            userResponse = userContext.getFeignClient(UserClient.class).getUserById(1L);
        }catch(Exception e){
            userContext.setResponse(e);
        }
    }

    @When("user reads a non-existing user information")
    public void userReadsANonExistingUserInformation() {
        try{
            userResponse = userContext.getFeignClient(UserClient.class).getUserById(1L);
        }catch(Exception e){
            userContext.setResponse(e);
        }
    }
}
