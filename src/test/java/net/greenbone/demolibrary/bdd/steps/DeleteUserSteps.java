package net.greenbone.demolibrary.bdd.steps;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import io.cucumber.java.en.When;
import net.greenbone.demolibrary.bdd.helper.adapter.http.client.BookClient;
import net.greenbone.demolibrary.bdd.helper.adapter.http.client.UserClient;

import java.util.Map;

public class DeleteUserSteps {
    private Map<String,String> message;

    @When("user tries to delete another user")
    public void userTriesToDeleteAnotherUser() {
        Feign.Builder encoder = Feign.builder() //Feign client, http rest client gson json ein und auslesen
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder());
        UserClient userClient = encoder.target(UserClient.class, "http://localhost:8081");
        message = userClient.deleteUser(1L);
    }

    @When("user tries to delete a non-existing user")
    public void userTriesToDeleteANonExistingUser() {

    }
}
