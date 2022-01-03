package net.greenbone.demolibrary.bdd.steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.greenbone.demolibrary.bdd.helper.adapter.context.UserContext;
import net.greenbone.demolibrary.domain.aggregates.ApplicationUser;
import net.greenbone.demolibrary.domain.enums.Role;

import static org.junit.Assert.assertEquals;

@RequiredArgsConstructor
@Slf4j
public class LoginAndPermissionsSteps {

    private final UserContext userContext;
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
        this.userContext.setEmail(email);
        this.userContext.setPassword(password);
        this.userContext.setResponseStatusCode(200);
    }

    @Then("user is logged in")
    public void userIsLoggedIn() {
        assertEquals(userContext.getResponseStatusCode().intValue(), 200);
    }

    @Given("user is of role {string}")
    public void userIsOfRole(String role) {
        assertEquals(applicationUser.getRole().name().toLowerCase(), role.toLowerCase());
        log.info("user is of role ADMIN");
    }

    @Given("admin is logged in")
    public void adminIsLoggedIn() {
        this.login(applicationUser.getEmail(), applicationUser.getPassword());
        this.userIsOfRole("ADMIN");
    }
}
