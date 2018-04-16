Feature: Checking login form

  Background:
    Given I open the public login page

  Scenario: Valid login
    When I enter valid 'username' and 'password'
    And I click login button
    Then I verify that user is logged in to profile