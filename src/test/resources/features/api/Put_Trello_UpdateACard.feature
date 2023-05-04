Feature: Trello Update A Card
  Scenario: Put request for update card
    Given Api kullanicisi "1/cards/6453bb59ddc5eaec4dba4de6" path parametrelerini set eder
    Then Query paramlar olusturulur