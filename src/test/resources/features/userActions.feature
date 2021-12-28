Feature: user does some kind of illegal actions
      # in ein anderes feature
  Scenario: User is not of role "Admin"
    Given user is logged in
    When user tries to create a book
    #needs to be specified
    Then an Exception is returned

  Scenario: User is not logged in
    When user tries to create a  book
    #needs to be specified
    Then an Exception is returned

