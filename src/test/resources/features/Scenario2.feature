Feature: flight search with infinite scroll validation and specific flight verification

  Scenario: Search flights from home
    Given I should be on the home screen
    When I set leaving from to
    And I set going to to
    And I set travel dates from next Friday to next week Friday
    And I search for flights
    Then the select flight screen should be displayed
    And at least one flight result is shown
    And I store a specific flight from the results
    And the specific flight should appear in the list
    And infinite scroll should load more results or reach the end