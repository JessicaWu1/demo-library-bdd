Feature: lending a Book

  Background:
    Given user is logged in

  Scenario: User is trying to borrow/lend a book
    When user borrows an existing book
    Then the relevant information to the borrowed book is returned

  Scenario: User is trying to lend/borrow a non-existing book
    When user tries to lend a non-existing book
    Then the user gets a Not Found message
    
