
  Feature: Used to create a new user

    Scenario: Success Response

      Given Api kullanicisi "api/register" path parametreleri set eder
      Then Register Customer icin gerekli Request Body "Ayse","Hoca","aysehoca","ay@gmail.com","1234.abc","1234.abc","customer","053621234567","44546545464546" hazirla
      Then Register Customer icin Post request gonder