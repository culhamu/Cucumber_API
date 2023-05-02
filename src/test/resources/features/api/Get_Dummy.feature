@API
  Feature: Get User by id
    Scenario: GET request gonderdigimizde donen response assert etme

      Given Api kullanicisi "api/v1/employee/3" path parametrelerini set eder
      Then Response body icin gerekli Request Body "success", 3, "Ashton Cox", 86000 , 66 , "", "Successfully! Record has been fetched."  hazirla
      Then Donen response'i kaydet
      And Response body ile expected body karsilastir
