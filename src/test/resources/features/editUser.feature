Feature: edit user

  Scenario: trying to edit user information (credentials, name etc.)
    Given user is logged in
    When user edits their information
    Then a success message is received

  Scenario: user tries to edit a non-existing user
    Given admin is logged in
    When admin edits user information from a non-existing user
    Then the user gets a Not Found message