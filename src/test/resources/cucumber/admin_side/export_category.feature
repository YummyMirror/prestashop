Feature: Export category manipulations

  Scenario: Export with existing categories
    Given I open the categories page
    When I click the 'Export' button
    Then I verify that 'csv' file is downloaded
    And Name of the file contains 'category' and date of download