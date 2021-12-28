Feature: deleting a user

  Scenario: deleting an existing user
    Given user is of role 'ADMIN'
    And user is logged in
    When user tries to delete another user
    Then status code 200 is returned

  Scenario: deleting a non-existing user
    Given user is logged in
    And user is of role 'ADMIN'
    When user tries to delete a non-existing user
    Then status code 404 is returned