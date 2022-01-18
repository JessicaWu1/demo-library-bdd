Feature: reading user information

  Scenario: user is trying to read user information
    Given user is logged in
    When user reads their information
    Then the information of the user is returned

  Scenario: user reads user information from a non-existing user
    Given user is logged in
    When user reads a non-existing user information
    Then the user gets a Not Found message

  Scenario: admin reads user information from a non-existing user
    Given admin is logged in
    When admin reads a non-existing user information
    Then the user gets a Not Found message