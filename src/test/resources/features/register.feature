Feature: Register a user

  Scenario: user send SMS OTP with valid phone number
    Given user send post request to sms OTP
    When user send post request to OTP verify
    Then user send post request to register with valid personal info