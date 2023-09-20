Feature: Top Up money with card
  Scenario: user top up money with card
    Given user sign in to account
    When user verify sign in
    And user post request to top up money with card
    Then user post request to success card top up