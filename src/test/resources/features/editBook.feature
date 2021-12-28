Feature: Edit Book

  Scenario: user does change fields in a Book
    Given user is logged in
    And user is of role "ADMIN"
    When user edits something in an existing book
    Then status code 200 is returned

#really needed??
  Scenario: user does not change any field in a Book
    Given user is logged in
    And user is of role "ADMIN"
    When user does not edit a book
    Then nothing should happen
    And status code 200 is returned

  Scenario: trying to edit a non-existing book
    Given user is logged in
    And user is of role "ADMIN"
    When user tries to edit a non-existing book
    Then status code 404 is returned
