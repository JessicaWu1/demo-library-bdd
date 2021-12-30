Feature: delete Book

  Scenario: user deletes an existing book
    Given user is logged in
    And user is of role "ADMIN"
    When user tries to delete an existing book
    Then the book information is shown

  Scenario: user deletes a non existing book
    Given user is logged in
    And user is of role "ADMIN"
    When user tries to delete a non-existing book
    Then the user gets a Null Pointer Exception

