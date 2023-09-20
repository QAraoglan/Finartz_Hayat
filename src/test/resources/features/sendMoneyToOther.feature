Feature: send money to other customer

  Scenario: user send money to other customer
    Given user sign in to account
    When user verify sign in
    Then user send money to other customer
    And user complete send money to other customer