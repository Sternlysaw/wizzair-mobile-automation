Feature: Navigate to Select Flight

  Scenario: Search flights from home
    Given I should be on the home screen
    When I search for flights
    Then the select flight screen should be displayed