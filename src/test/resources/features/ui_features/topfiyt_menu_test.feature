@Test01
Feature: Navigate to Topfiyt "Who is Topfiyt For?" page

  Scenario: User clicks on "Who is Topfiyt For?" link from main menu
    Given User navigates to "https://www.topfiyt.com/"
    When User clicks on the "Who is Topfiyt For?" link
    Then User should be redirected to the corresponding page
