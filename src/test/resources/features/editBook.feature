Feature: Edit Book

  Scenario: user does change fields in a Book
    Given admin is logged in
    When user edits something in an existing book
    Then a success message is received

  Scenario: trying to edit a non-existing book
    Given admin is logged in
    When user tries to edit a non-existing book
    Then the user gets a Not Found Exception