

Feature: You can log in to the system with your email and password

  Scenario: Success Response

    Given Api kullanicisi "api/login" path parametrelerini set eder
    Then Login icin gerekli "email" ve "password" girilir
    Then Login icin Post request gonderilir