Feature: reading information of a book

  Background:
    Given user is logged in

  #background
  #when nochmal
  #regulärer ausdruck erfolgerich eingeloggt -> optionales successful einfügen
  Scenario: user reads book information
    When user reads book information
    Then the information is returned

  Scenario: user tries to read non-existing book information
    When user reads non-existing book information
    Then the user gets a Not Found message
