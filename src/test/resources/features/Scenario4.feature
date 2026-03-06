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
    When I sign in if prompted with email "testersenne@gmail.com" and password "TesterSenne123@"
    When I see want a discount page
    And I do not want a discount
    Then I am on the passenger screen
    When I click no special assistance
    And I click next on the passenger screen
    Then I am on the Baggages screen
    When I click small checked in baggage
    And I scroll until I see sport equipment
    And I click two cabin bags and priority
    And I click next on the Baggages page
    Then I am on the seats page
    When I choose seat later
    And I click next on the seats page
    Then I am on the services page
    When I click next in the services page
    Then I am on the insurance page
    When I select travel insurance
    And I click select on the insurance page
    Then I am on the declaration page
    When I click confirm on the declaration page
    When I fill billing details if required
    When I pay by card