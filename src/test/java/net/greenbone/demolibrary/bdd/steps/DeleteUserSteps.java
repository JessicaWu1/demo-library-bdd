package net.greenbone.demolibrary.bdd.steps;

import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.greenbone.demolibrary.bdd.helper.adapter.context.UserContext;
import net.greenbone.demolibrary.bdd.helper.adapter.http.client.UserClient;


@RequiredArgsConstructor
@Slf4j
public class DeleteUserSteps {
    private final UserContext userContext;

    @When("admin tries to delete another user")
    public void adminTriesToDeleteAnotherUser() {
        int status = userContext.getFeignClient(UserClient.class)
                .deleteUser(2L)
                .status();
        log.info("Status " + status);
        userContext.setResponseStatusCode(status);
    }

    @When("admin tries to delete a non-existing user")
    public void adminTriesToDeleteANonExistingUser() {
        try{
            this.userContext.setResponseStatusCode(
                    userContext.getFeignClient(UserClient.class)
                            .deleteUser(3L)
                            .status());
        }catch(Exception e){
            userContext.setResponse(e);
        }
    }
}
