Feature: creating a new user

  Scenario: create a new user
    Given user is of role 'ADMIN'
    And user is logged in
    When trying to create a user
    Then the created user information is shown

  Scenario: admin does not give an initial password to the created user
    Given user is of role 'ADMIN'
    And user is logged in
    When trying to create a user without an initial password
    Then the user gets a Bad Request Status