Feature: deleting a user

  Scenario: deleting an existing user
    Given admin is logged in
    When admin tries to delete another user
    Then a success message is received

  Scenario: deleting a non-existing user
    Given admin is logged in
    When admin tries to delete a non-existing user
    Then the user gets a Not Found Exception