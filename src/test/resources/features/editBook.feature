Feature: Edit Book

  Scenario: user does change fields in a Book
    Given user is logged in
    And user is of role "ADMIN"
    When user edits something in an existing book
    Then expect status code 200
#really needed??
  Scenario: user does not change any field in a Book
    Given user is logged in
    And user is of role "ADMIN"
    When user does not edit a book
    Then nothing hould happen
    And expect status code 200
