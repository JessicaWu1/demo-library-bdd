Feature: user is not logged in or not an
      # in ein anderes feature
  Scenario: User is not of role "Admin"
    Given user is logged in
    When user tries to create a book
    #needs to be specified
    Then an Exception is returned

  Scenario: User is not logged in
    Given user is of role 'ADMIN'
    When user tries to create a  book
    #needs to be specified
    Then an Exception is returned

