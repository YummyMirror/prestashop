Feature: Checking login form

  Background:
    Given I open the administration login page

  Scenario Outline: Valid login
    When I enter both valid '<username>' and '<password>'
    And I click the login page
    Then I verify that user is logged in

    Examples: Valid login data
      | username    | password |
      | 123@mail.ru | password |

  Scenario Outline: Invalid login
    When I enter invalid '<username>' and '<password>'
    And I click the login page
    Then I verify that user is not logged in

    Examples: Invalid login data
      | username     | password  |
      | 123@mail.ru  | password1 |
      | 1234@mail.ru | password  |
      | 123@mail.ru  |           |
      |              | password  |