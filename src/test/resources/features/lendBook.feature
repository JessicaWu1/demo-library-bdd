Feature: lending a Book

  Scenario: User is trying to borrow/lend a book
    Given user is logged in
    When user tries to lend an existing book
    Then user is shown the return date of that book

    Scenario: User is trying to lend/borrow a non-existing book
      Given user is logged in
      When user tries to lend a non-existing book
      Then the user gets a Not Found Exception
