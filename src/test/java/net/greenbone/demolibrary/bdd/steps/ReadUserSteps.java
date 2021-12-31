package net.greenbone.demolibrary.bdd.steps;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.greenbone.demolibrary.bdd.helper.adapter.http.client.UserClient;
import net.greenbone.demolibrary.representations.response.UserResponse;
import org.hamcrest.Matchers;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class ReadUserSteps {

    private UserResponse userResponse;
    private UserClient userClient;

    @Before
    public void setUp(){
        Feign.Builder encoder = Feign.builder() //Feign client, http rest client gson json ein und auslesen
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder());
        userClient = encoder.target(UserClient.class, "http://localhost:8081");
    }

    @When("user tries to read a specified user's information")
    public void userTriesToReadASpecifiedUserSInformation() {
        userResponse = userClient.getUserById(1L);
    }

    @Then("the information to the specified user is shown")
    public void theInformationToTheSpecifiedUserIsShown() {
        assertNotNull(userResponse);
        assertThat(1L, Matchers.is(userResponse.getId()));
    }

    @When("user tries to read a non-existing user information")
    public void userTriesToReadANonExistingUserInformation() {
    }

    @Then("a Null Pointer Exception is thrown")
    public void aNotFoundExceptionIsThrown() {
        assertThatThrownBy(() -> userClient.getUserById(3L))
                .isInstanceOf(NullPointerException.class);
    }
}
