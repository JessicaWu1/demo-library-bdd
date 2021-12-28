package net.greenbone.demolibrary.bdd.steps;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateBookSteps {
    @Given("user has role {string}")
    public void givenUserHasRole(String arg0) {
    }
    
    @And("user is logged in")
    public void userIsLoggedIn() {
        
    }


    @When("user creates a new book with the needed information")
    public void userCreatesANewBookWithTheNeededInformation() {
        /*ProcedureDto procedureDto = new ProcedureDto(null, 250f);
        Feign.Builder encoder = Feign.builder() //Feign client, http rest client gson json ein und auslesen
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder());
        ProcedureClient procedureClient = encoder.target(ProcedureClient.class, "http://localhost:8081");
        responseDto = procedureClient.createProcedure(procedureDto);*/
    }

    @Then("book is created")
    public void bookIsCreated() {
        
    }

    @And("status code {int} is returned")
    public void statusCodeIsReturned(int arg0) {
    }

    @When("user tries to create a Book with missing information")
    public void userTriesToCreateABookWithMissingInformation() {
    }
}
