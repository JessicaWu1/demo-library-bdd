Feature: Edit Book

  # gleiche zeitform
  # status code -> generisch
  # something anything etc. nicht verwenden
  Scenario: user does change fields in a Book
    Given admin is logged in
    When user edits a book in an existing book
    Then a success message is received

  Scenario: trying to edit a non-existing book
    Given admin is logged in
    When user tries to edit a non-existing book
    Then the user gets a Not Found Exception