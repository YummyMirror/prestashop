Feature: Export category manipulations

  @Admin
  @Category
  Scenario: Export with existing categories
    Given I open the categories page
    When I click the 'Export' button
    Then I verify that 'csv' file is downloaded
    And Name of the file contains 'csv'