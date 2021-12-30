Feature: Edit Book

  Scenario: user does change fields in a Book
    Given user is logged in
    And user is of role "ADMIN"
    When user edits something in an existing book
    Then a success message is received

  Scenario: trying to edit a non-existing book
    Given user is logged in
    And user is of role "ADMIN"
    When user tries to edit a non-existing book
    Then the user gets a Not Found Exception
