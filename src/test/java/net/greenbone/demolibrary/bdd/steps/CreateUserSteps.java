package net.greenbone.demolibrary.bdd.steps;

import feign.Feign;
import feign.FeignException;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.greenbone.demolibrary.bdd.helper.adapter.context.UserContext;
import net.greenbone.demolibrary.bdd.helper.adapter.http.client.BookClient;
import net.greenbone.demolibrary.bdd.helper.adapter.http.client.UserClient;
import net.greenbone.demolibrary.representations.request.UserRequest;
import net.greenbone.demolibrary.representations.response.UserResponse;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.*;

@RequiredArgsConstructor
@Slf4j
public class CreateUserSteps {

    private final UserContext userContext;

    private UserResponse userResponse;
    private UserRequest userRequest;

    @When("trying to create a user")
    public void tryingToCreateAUser() {
        try{
            userRequest = UserRequest.builder()
                    .name("Toller Name")
                    .email("maxmustermann@gmail.com")
                    .password("password")
                    .role("ADMIN")
                    .build();

            userResponse =  userContext.getFeignClient(UserClient.class).createUser(userRequest);
        }catch(Exception e){
            log.info("UserRequest: "+userRequest);
            log.info("UserResponse: "+userResponse);
            log.info("Exception e: ", e);
            userContext.setResponse(e);
        }
    }

    @When("trying to create a user without an initial password")
    public void tryingToCreateAUserWithoutAnInitialPassword() {
        userRequest = UserRequest.builder()
                .name("Toller Name")
                .email("maxmustermann@gmail.com")
                .role("ADMIN")
                .build();
        try{
            userResponse =  userContext.getFeignClient(UserClient.class).createUser(userRequest);
        }catch(Exception e){
            userContext.setResponse(e);
        }
    }

    @Then("the created user information is shown")
    public void theCreatedUserInformationIsShown() {
        log.info("UserResponse" + userResponse);
        assertThat(userRequest.getEmail(), Matchers.is(userResponse.getEmail()));
        assertThat(userRequest.getName(), Matchers.is(userResponse.getName()));
        assertThat(userRequest.getPassword(), Matchers.is(userResponse.getPassword()));
        assertThat(userRequest.getRole(), Matchers.is(userResponse.getRole()));
        assertEquals(0.0F, userResponse.getLateFees(), 0.3F);
        assertThat(new ArrayList<>(), Matchers.is(userResponse.getBorrowedBooks()));
    }
}
