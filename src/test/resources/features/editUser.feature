Feature: edit user

  Scenario: trying to edit user information (credentials, name etc.)
    Given user is logged in
    When user tries to edit a specified user information
    Then a success message is shown

  Scenario: user tries to edit a non-existing user
    Given user is logged in
    When user tries to edit user information from a non-existing user
    Then the user gets a Not Found Exception