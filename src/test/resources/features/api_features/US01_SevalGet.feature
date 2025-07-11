@US01SevalGet
  Feature: US01 Seval Get
    @Admin
    Scenario: TC01 | Seval Get Request
      Given I send a GET request to "https://reqres.in/api/users/2"
      When Get request is sent and response is received
      Then status code should be 200