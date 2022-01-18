Feature: user tries to read a collection of books as list

  Background:
    Given user is logged in

  Scenario: user wants all books
    When user wants all books
    Then a List of those books is returned 3

  Scenario: user wants all books from an author
    When user wants all books from one author 'Newcomer Author'
    Then a List of those books is returned 3

  Scenario: user searches for a specific book title
    When user wants all books with the title 'Greatest Book you\'ll ever read'
    Then a List of those books is returned 3