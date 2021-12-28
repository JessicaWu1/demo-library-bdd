Feature: edit user

  Scenario: trying to edit user information (credentials, name etc.)
    Given user is logged in
    When user tries to edit a specified user information
    Then status code 200 is returned

  Scenario: user tries to edit a non-existing user
    Given admin is logged in
    When admin tries to edit user information from a non-existing user
    Then status code 404 is returned