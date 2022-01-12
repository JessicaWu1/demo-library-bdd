Feature: reading information of a book
  #background
  #when nochmal
  #regulärer ausdruck erfolgerich eingeloggt -> optionales successful einfügen
  Scenario: user reads book information
    Given user is logged in
    When user tries to read book information
    Then the information is shown

  Scenario: user tries to read non-existing book information
    Given user is logged in
    When user tries to read non-existing book information
    Then the user gets a Not Found Exception
