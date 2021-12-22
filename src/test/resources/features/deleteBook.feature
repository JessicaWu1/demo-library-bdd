Feature: delete Book

  Scenario: user deletes an existing book
    Given user is logged in
    And of role "ADMIN"
    When user tries to delete an existing book
    Then expect status code 200

  Scenario: user deletes a non existing book
    Given user is logged in
    And of role "ADMIN"
    When user tries to delete a non-existing book
    Then expect status code 404

