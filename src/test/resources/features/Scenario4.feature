Feature: Continue booking and reach sign in

  Scenario: Continue booking until sign in screen appears

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
    When I continue from summary
    When I sign in if prompted with email "testersenne@gmail.com" and password "Testersenne123@"
    When I see want a discount page
    And I do not want a discount
    Then I am on the passenger screen
    When I click no special assistance
    And I click next on the passenger screen