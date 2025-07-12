@US01SevalGet
  Feature: US01 Seval Get
    @Admin
    Scenario: TC01 | Seval Get Request
      Given I send a GET request to "https://petstore.swagger.io/v2/pet/findByStatus?status=available"
      When Get request is sent and response is received
      Then status code should be 200