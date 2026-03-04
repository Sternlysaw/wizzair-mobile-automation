Feature: Scenario 3 scaffold - start booking and choose bundle

  Scenario: Start booking and choose Quick Travel bundle
    Given I should be on the home screen
    When I set leaving from to
    And I set going to to
    And I set travel dates from next Friday to next week Friday
    And I search for flights
    Then the select flight screen should be displayed
    When I select the first available flight
    Then the bundle selection screen should be displayed
    When I select the Quick Travel bundle
    And I continue from bundle selection
    When I select the first available flight
    Then the bundle selection screen should be displayed
    When I select the Pack & Save bundle
    And I continue from bundle selection
    Then the trip summary screen should be displayed