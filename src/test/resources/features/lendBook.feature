Feature: lending a Book

  Scenario: User is lending a book
    Given user is logged in
    When user tries to lend a book
    #nutzer soll das buch ausgeliehen haben -> spezifikation: in der datenbank als ausgeliehen markieren etc soll hier nicht rein?
    Then user gets the book
    And user is shown the return date of that book
    And expect status code 200