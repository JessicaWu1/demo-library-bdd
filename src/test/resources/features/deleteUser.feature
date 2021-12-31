Feature: deleting a user

  Scenario: deleting an existing user
    Given user is of role "ADMIN"
    And user is logged in
    When user tries to delete another user
    Then a success message is shown

  Scenario: deleting a non-existing user
    Given user is logged in
    And user is of role "ADMIN"
    When user tries to delete a non-existing user
    Then the user gets a Not Found Exception