
 @API
  Feature: Used to create a new user

    Scenario: Success Response

      Given Api kullanicisi "api/register" path parametrelerini set eder
      Then Register Customer icin gerekli Request Body "Mustafa","Talebe","Mustafa","mustafa@mail.com","12345678","12345678","customer","6547893218","44546545464546" hazirla
      Then Register Customer icin Post request gonder