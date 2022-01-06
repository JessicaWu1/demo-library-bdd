package net.greenbone.demolibrary.bdd.steps;

import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import net.greenbone.demolibrary.bdd.helper.adapter.context.UserContext;
import net.greenbone.demolibrary.bdd.helper.adapter.http.client.UserClient;
import net.greenbone.demolibrary.representations.request.UserRequest;

import java.util.ArrayList;

@RequiredArgsConstructor
public class EditUserSteps {

    private final UserContext userContext;
    private UserRequest userRequest;

    @When("admin tries to edit user information from a non-existing user")
    public void adminTriesToEditUserInformationFromANonExistingUser() {
        userRequest = UserRequest.builder()
                .name("Max Muster")
                .password("newPassword")
                .lateFees(1.0F)
                .borrowedBooks(new ArrayList<>())
                .role("ADMIN")
                .email("maxmustermann@example.com")
                .build();
        try{
            userContext.getFeignClient(UserClient.class).updateUser(2L, userRequest,this.userContext.getHeaderMap());
        }catch(Exception e){
            userContext.setResponse(e);
        }
    }

    @When("user tries to edit their information")
    public void userTriesToEditATheirInformation() {
        userRequest = UserRequest.builder()
                .name("Max Muster")
                .password("newPassword")
                .lateFees(1.0F)
                .borrowedBooks(new ArrayList<>())
                .role("ADMIN")
                .email("maxmustermann@example.com")
                .build();
        userContext.setResponseStatusCode(userContext.getFeignClient(UserClient.class)
                .updateUser(1L, userRequest,this.userContext.getHeaderMap())
                .status());
    }
}
