Feature: creating a new user

  Scenario: create a new user
    Given user that tries to create a new user is of role 'ADMIN'
    And user is logged in
    When trying to create a user
    Then user is created
    And status code 201 is returned

  Scenario: admin does not give an intial password to the created user
    Given user that tries to create a new user is of role 'ADMIN'
    And user is logged in
    When trying to create a user without an initial password
    #needs to be specified
    Then an Exception is returned