Feature: lending a Book

  Scenario: User is trying to borrow/lend a book
    Given user is logged in
    When user tries to lend an existing book
    #nutzer soll das buch ausgeliehen haben -> spezifikation: in der datenbank als ausgeliehen markieren etc soll hier nicht rein?
    Then user gets the book
    And user is shown the return date of that book
    And expect status code 200

    Scenario: User is trying to lend/borrow a non-existing book
      Given user is logged in
      When user tries to lend a non-existing book
      Then eypect status code 404
