Feature: deleting a user

  Background:
    Given admin is logged in

  Scenario: deleting an existing user
    When admin deletes another user
    Then a success message is received

  Scenario: deleting a non-existing user
    When admin tries to delete a non-existing user
    Then the user gets a Not Found message