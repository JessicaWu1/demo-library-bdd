package net.greenbone.demolibrary.bdd.steps;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateBookSteps {

    /*@Given("^(the user|another user|the invited user|the invited managed user) logs in( successfully)?$")
    public void theUserIsLoggedIn(String userType, String successfully) {
        log.info("'{}' logs in with user:'{}' to domain:'{}ui/'",
                userType, emails.get(userType), this.userContext.baseUrl);

        this.login(this.emails.get(userType), passwords.get(userType), successfully != null);
    }
    private void login(String emailAddress, String password, boolean withSuccessCheck) {
        try {
            this.userContext.tokenResponse = this.userContext.keyCloakClient
                    .login(this.userContext.BDD_REALM_ID, new KeyCloakLoginRequestDto(emailAddress, password, this.userContext.CLIENT_ID));
            this.userContext.usedEmail = emailAddress;
            this.userContext.usedPassword = password;
            this.userContext.setResponseStatusCode(200);
        } catch (Exception e) {
            if (withSuccessCheck) {
                throw e;
            }
            this.userContext.tokenResponse = null;
            this.userContext.usedEmail = null;
            this.userContext.usedPassword = null;
            this.userContext.setResponse(e);
        }
    }
    */

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
