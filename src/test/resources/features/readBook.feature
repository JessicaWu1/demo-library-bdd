Feature: reading information of a book

  Scenario: user reads book information
    Given user is logged in
    When user tries to read book information
    Then the information is shown

  Scenario: user tries to read non-existing book information
    Given user is logged in
    When user tries to read non-existing book information
    Then the user gets a Null Pointer Exception
