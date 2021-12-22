Feature: create a new Book

  Scenario: Book without a name
    Given user has role "ADMIN"
    And user is logged in
    When user tries to create a Book without a name
    #needs to be specified
    Then an Exception is returned

  Scenario: Book without a publishing year
    Given user has role "ADMIN"
    And user is logged in
    When user tries to create a Book without a publishing year
    #needs to be specified
    Then an Exception is returned

  Scenario: Book without an author
    Given user has role "ADMIN"
    And user is logged in
    When user tries to create a Book without an author
    #needs to be specified
    Then an Exception is returned

  Scenario: Book without quantity specification
    Given user has role "ADMIN"
    And user is logged in
    When user tries to create a Book without a quantity specification
    #needs to be specified
    Then an Exception is returned

  Scenario: Book without a description
    Given user has role "ADMIN"
    And user is logged in
    When user tries to create a Book without a name
    #needs to be specified
    Then an Exception is returned

    # in ein anderes feature
  Scenario: User is not of role "Admin"
    Given user is logged in
    When user tries to create a book
    #needs to be specified
    Then an Exception is returned

  Scenario: User is not logged in
    When user tries to create a  book
    #needs to be specified
    Then an Exception is returned