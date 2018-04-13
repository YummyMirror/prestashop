Feature: Category manipulations

  Scenario: Create a new category
    Given Number of categories before creation
    When I fill the form with data:
      | name        | isDisplayed | description | coverImage |
      | NewCategory | true        | Some Desc   | D:\\1.jpg  |
    And I click the 'Save' button
    Then Number of groups is incremented