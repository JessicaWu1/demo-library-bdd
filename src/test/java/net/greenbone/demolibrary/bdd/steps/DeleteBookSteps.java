package net.greenbone.demolibrary.bdd.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import net.greenbone.demolibrary.representations.request.UserRequest;

public class DeleteBookSteps {
    @And("user is of role {string}")
    public void userIsOfRole(String arg0, UserRequest user) {
    }

    @When("user tries to delete an existing book")
    public void userTriesToDeleteAnExistingBook() {
    }

    @When("user tries to delete a non-existing book")
    public void userTriesToDeleteANonExistingBook() {
    }
}
