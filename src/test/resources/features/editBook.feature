Feature: Edit Book

  Background:
    Given admin is logged in
  # gleiche zeitform
  # status code -> generisch
  # something anything etc. nicht verwenden
  Scenario: user does change fields in a Book
    When user edits an existing book
    Then a success message is received

  Scenario: trying to edit a non-existing book
    When user tries to edit a non-existing book
    Then the user gets a Not Found message