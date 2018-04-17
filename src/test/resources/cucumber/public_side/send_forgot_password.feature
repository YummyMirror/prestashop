Feature: Forgot password sending

  @Public
  @Wiser
  Scenario: Checking that forgot password email sends
    Given I open the 'Forgot password' page
    When I enter the 'email'
    And I click the 'Send email' button
    Then I check that one email is sent
    And I check that sender is correct
    And I check that receiver is correct