Feature: Withdraw money functionality
  Scenario: user withdraw money from iban
    Given user sign in to account
    When user verify sign in
    Then user withdraw money from iban "123456789"
