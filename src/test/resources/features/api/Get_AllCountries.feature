

Feature: You can log in to the system with your email and password

  Scenario: Success Response

    Given Api kullanicisi "api/profile/allCountries" path parametrelerini set eder
    Then AllCountries icin Get request gonderilir