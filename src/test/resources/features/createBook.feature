Feature: create a new Book

  Background:
    Given admin is logged in

  Scenario: user creates a new book
    When user creates a new book with the needed information
    Then the book information is shown
    #Then a success message is received
    #And book information is returned

  Scenario: Book without a name
    When user tries to create a Book with missing information
    Then the user gets a Bad Request Exception