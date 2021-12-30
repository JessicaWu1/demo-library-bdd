package net.greenbone.demolibrary.bdd.steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.greenbone.demolibrary.domain.aggregates.ApplicationUser;
import net.greenbone.demolibrary.domain.enums.Role;

public class LoginAndPermissionsSteps {

    private ApplicationUser applicationUser;

    @Before
    @Given("user|admin is created")
    public void userAdminIsCreated(){
        applicationUser = ApplicationUser.builder()
                .email("maxmuster@gmail.com")
                .password("password")
                .role(Role.ADMIN)
                .build();
    }


    @When("user|admin tries to log in with his email address {string} and password {string}")
    public void login(String email, String password){
        email.equals(applicationUser.getEmail());
        password.equals(applicationUser.getPassword());
    }

    @Then("^user is logged in")
    public void userIsLoggedIn() {
        this.login(applicationUser.getEmail(), applicationUser.getPassword());
    }

    @Given("user is of role {string}")
    public void userIsOfRoleADMIN(String role) {
        applicationUser.getRole().name().toLowerCase().equals(role.toLowerCase());
    }


    /*@Given("^(the user|another user|the invited user|the invited managed user) logs in( successfully)?$")
    public void theUserIsLoggedIn(String userType, String successfully) {
        log.info("'{}' logs in with user:'{}' to domain:'{}ui/'",
                userType, emails.get(userType), this.userContext.baseUrl);

        this.login(this.emails.get(userType), passwords.get(userType), successfully != null);
    }
    */

}
