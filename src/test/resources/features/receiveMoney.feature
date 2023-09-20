Feature: receive money feature
  Scenario: receive money feature
    Given user sign in to account
    When user verify sign in
    And user want to receive money
    And user cancel money receive
    Then user complete money receive