Feature: nasa
  Scenario: APOD
    
    Given Api kullanicisi "planetary/apod" path parametrelerini set eder
    Then get sorgusunu olusturur