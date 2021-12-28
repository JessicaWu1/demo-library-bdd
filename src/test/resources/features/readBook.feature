Feature: reading information of a book

  Scenario: user reads book information
    Given user is logged in
    When user tries to read book information
    Then the information is shown
    And status code 200 is returned

  Scenario: user tries to read non-existing book information
    Given user is logged in
    When user tries to read non-existing book information
    Then status code 404 is returned
