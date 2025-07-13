@US02SevalPost
Feature: US02 Seval Post - Pet Image Upload

  @Admin
  Scenario: TC01 | Upload Pet Image with Additional Metadata
    Given I have a pet with ID "1"
    And I have an image file "img.png"
    And I have additional metadata "me"
    When I send a POST request to upload the image
    Then the response status code should be 200
    And the response should contain upload confirmation

  @Admin
  Scenario: TC02 | Upload Pet Image without Additional Metadata
    Given I have a pet with ID "1"
    And I have an image file "img.png"
    When I send a POST request to upload the image
    Then the response status code should be 200
    And the response should contain upload confirmation

  @Admin
  Scenario: TC03 | Upload Pet Image with Pet ID
    Given I have a pet with ID "999999"
    And I have an image file "img.png"
    And I have additional metadata "test metadata"
    When I send a POST request to upload the image
    Then the response status code should be 200
    And the response should contain upload confirmation