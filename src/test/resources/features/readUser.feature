Feature: reading user information

  Scenario: user is trying to read user information
    Given user is logged in
    When user tries to read a specified user's information
    Then the information to the specified user is shown
    And status code 200 is returned

  Scenario: user tries to read user information from a non-existing user
    Given user is logged in
    When user tries to read a non-existing user information
    Then status code 404 s returned