Feature: delete Book

  Background:
    Given admin is logged in

  Scenario: user deletes an existing book
    When user deletes an existing book
    Then a success message is received
    When user tries reading the book
    Then the user gets a Not Found message

  Scenario: user deletes a non existing book
    When user tries to delete a non-existing book
    Then the user gets a Not Found message
