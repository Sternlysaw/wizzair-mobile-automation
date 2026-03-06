Feature: Deep linking

Scenario: Deep Link + First Launch + Permissions
When I open the select flight deep link on first launch
Then the select flight screen should be displayed
And the select flight screen should match the deep link