Feature: user tries to read a collection of books as list
  #readbooklist; readbookaslist ist doof

  Scenario: user searches for books from a specific author
    Given user is logged in
    When user searches for all books from a specific author
    #search -> filtern dann nochmal einzeln
    Then a List of those books is returned