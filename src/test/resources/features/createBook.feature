Feature: create a new Book

  Background:
    Given admin is logged in

  Scenario: user creates a new book
    When user creates a new book with the needed information
    Then a success message is received
    And the book information is returned

  Scenario: Creating a book with missing information
    When user creates a Book with missing information
    Then the user gets a Bad Request message