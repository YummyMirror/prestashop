Feature: Category manipulations

  Background:
    Given Number of categories before creation

  Scenario: Create a new category
    When I fill the form with data:
      | name        | isDisplayed | description | coverImage                     |
      | NewCategory | true        | Some Desc   | src/test/resources/image/1.jpg |
    And I click the 'Save' button
    Then Number of groups is incremented

  Scenario: Delete created category
    When I delete random category
    Then Number of categories is decremented