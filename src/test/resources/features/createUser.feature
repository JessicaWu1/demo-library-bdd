Feature: creating a new user

  Background:
    Given admin is logged in

  Scenario: create a new user
    When trying to create a user
    Then the created user information is shown

  Scenario: admin does not give an initial password to the created user
    When trying to create a user without an initial password
    Then the user gets a Bad Request message