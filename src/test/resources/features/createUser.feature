Feature: creating a new user

  Scenario: create a new user
    Given user is of role 'ADMIN'
    And user is logged in
    When trying to create a user
    Then user is created
    And status code 201 is returned

  Scenario: admin does not give an initial password to the created user
    Given user is of role 'ADMIN'
    And user is logged in
    When trying to create a user without an initial password
    #needs to be specified
    Then status code 400 is returned