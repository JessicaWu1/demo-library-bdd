Feature: return a book

  Scenario: user returns a lend book
    Given user is logged in
    When user tries to return a book
    Then a success message is received
