Feature: Logging in and getting a role

  Scenario: User tries to Log in
    Given user|admin is created
    When user|admin tries to log in with his email address "maxmustermann@gmail.com" and password "password"
    Then user is logged in