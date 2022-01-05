Feature: Logging in and getting a role

  Scenario: User tries to Log in
    When user tries to log in with his email address and password
    Then user is logged in