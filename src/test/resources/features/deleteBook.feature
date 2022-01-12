Feature: delete Book

  Scenario: user deletes an existing book
    Given admin is logged in
    When user deletes an existing book
    Then a success message is received
    When user tries reading the book
    Then book does not exist

  Scenario: user deletes a non existing book
    Given admin is logged in
    When user tries to delete a non-existing book
    Then the user gets a Not Found message
