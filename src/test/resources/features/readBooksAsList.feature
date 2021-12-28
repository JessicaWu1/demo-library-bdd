Feature: user tries to read a collection of books as list

  Scenario: user searches for books from a specific author
    Given user is logged in
    When user searches for all books from a specific author
    Then a List of those books is returned
    And status code 200 is returned