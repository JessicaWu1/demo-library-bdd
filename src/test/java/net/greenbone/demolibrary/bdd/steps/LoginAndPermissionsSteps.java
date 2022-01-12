package net.greenbone.demolibrary.bdd.steps;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.greenbone.demolibrary.bdd.helper.adapter.context.UserContext;
import net.greenbone.demolibrary.bdd.helper.representations.KeyCloakLoginRequest;
import net.greenbone.demolibrary.domain.aggregates.ApplicationUser;
import net.greenbone.demolibrary.domain.enums.Role;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.AccessTokenResponse;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.contains;

@Slf4j
public class LoginAndPermissionsSteps {

    private final UserContext userContext;
    private ApplicationUser applicationUser;

    public LoginAndPermissionsSteps(UserContext userContext){
        this.userContext = userContext;
        this.applicationUser = ApplicationUser.builder()
                .email("maxmustermann@example.com")
                .password("password")
                .role(Role.ADMIN)
                .build();
        userContext.setEmail("maxmustermann@example.com");
        userContext.setPassword("password");
    }

    @When("user tries to log in with his email address and password")
    public void login(){
        try{
            this.userContext.setTokenResponse(
                    this.userContext.getKeycloakClient().login(
                            new KeyCloakLoginRequest("password", userContext.getEmail(), userContext.getPassword(), userContext.getClient_id(), userContext.getClient_secret()))
            );
        }catch(Exception e){
            log.info("Login failed: "+ e.getMessage() + e.getStackTrace());
            this.userContext.setTokenResponse(null);
            this.userContext.setResponse(e);
        }
    }

    @Then("user is logged in")
    public void userIsLoggedIn() {
        this.login();
    }

    @Given("user is of role {string}") //better
    public void userIsOfRole(String role) {
        DecodedJWT jwt = JWT.decode(userContext.getTokenResponse().getAccessToken());
        log.info("JWT " + jwt.getClaims().get("realm_access").asMap().get("roles").toString().contains(role));
        //ist das überhaupt richtig??
        assertTrue(
                jwt.getClaims()
                .get("realm_access")
                .asMap()
                .get("roles")
                .toString().contains(role));
        log.info("user is of role" + role);
    }

    @Given("admin is logged in")
    public void adminIsLoggedIn() {
        this.login();
        this.userIsOfRole("ADMIN");
    }
}
