Feature: user is not logged in or not an
      # in ein anderes feature
  Scenario: User is not of role "Admin"
    Given user is logged in
    When user tries to create a book
    #needs to be specified
    Then status code 403 is returned

  Scenario: User is not logged in
    When user does any action
    #needs to be specified
    Then status code 401 is returned

