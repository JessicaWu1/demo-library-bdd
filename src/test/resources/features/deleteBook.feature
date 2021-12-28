Feature: delete Book

  Scenario: user deletes an existing book
    Given user is logged in
    And user is of role "ADMIN"
    When user tries to delete an existing book
    Then status code 200 is returned

  Scenario: user deletes a non existing book
    Given user is logged in
    And user is of role "ADMIN"
    When user tries to delete a non-existing book
    Then status code 404 is returned

