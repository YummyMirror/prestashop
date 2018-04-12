Feature: Checking login form

  Scenario Outline: Valid login
    Given I open the administration login page
    When I enter both valid '<username>' and '<password>'
    And I click the login page
    Then I verify that user is logged in

    Examples: Valid login data
      | username    | password |
      | 123@mail.ru | password |