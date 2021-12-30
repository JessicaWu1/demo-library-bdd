Feature: create a new Book

  Scenario: user creates a new book
    Given user is of role "ADMIN"
    And user is logged in
    When user creates a new book with the needed information
    Then the book information is shown

  Scenario: Book without a name
    Given user is of role "ADMIN"
    And user is logged in
    When user tries to create a Book with missing information
    #might not be good to have this -> more on behaviour
    Then the user gets a Bad request Exception