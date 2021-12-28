Feature: create a new Book

  Scenario: user creates a new book
    Given user has role "ADMIN"
    And user is logged in
    When user creates a new book with the needed information
    Then book is created
    And status code 201 is returned

  Scenario: Book without a name
    Given user has role "ADMIN"
    And user is logged in
    When user tries to create a Book with missing information
    #needs to be specified
    Then status code 400 is returned