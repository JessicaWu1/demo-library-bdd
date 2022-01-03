package net.greenbone.demolibrary.bdd.steps;

import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import net.greenbone.demolibrary.bdd.helper.adapter.context.UserContext;
import net.greenbone.demolibrary.bdd.helper.adapter.http.client.UserClient;


@RequiredArgsConstructor
public class DeleteUserSteps {
    private final UserContext userContext;

    @When("admin tries to delete another user")
    public void adminTriesToDeleteAnotherUser() {
        userContext.setResponseStatusCode(userContext.getFeignClient(UserClient.class).deleteUser(1L).status());
    }

    @When("admin tries to delete a non-existing user")
    public void adminTriesToDeleteANonExistingUser() {
        try{
            userContext.getFeignClient(UserClient.class).deleteUser(3L);
        }catch(Exception e){
            userContext.setResponse(e);
        }
    }
}
