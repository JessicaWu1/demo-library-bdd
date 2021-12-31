package net.greenbone.demolibrary.bdd.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ReadUserSteps {
    @When("user tries to read a specified user's information")
    public void userTriesToReadASpecifiedUserSInformation() {
    }

    @Then("the information to the specified user is shown")
    public void theInformationToTheSpecifiedUserIsShown() {
    }

    @When("user tries to read a non-existing user information")
    public void userTriesToReadANonExistingUserInformation() {
    }

    @Then("a Not Found Exception is thrown")
    public void aNotFoundExceptionIsThrown() {

    }
}
