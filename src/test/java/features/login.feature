Feature: Facebook login page

  @no_param
  Scenario: Scenario have no parameter
    Given Open facebook application
    When Input to email textbox
    And Input password textbox
    And Click to Submit button
    Then Close application

  @param_mark
  Scenario: Scenario have parameter
    Given Open facebook application
    When Input to email textbox with "abcd@gmail.com"
    And Input password textbox with "123456"
    And Click to Submit button
    Then Close application

  @param_no_mark
  Scenario: Scenario have parameter
    Given Open facebook application
    When Input to email textbox with abcd@gmail.com
    And Input password textbox with 123456
    And Click to Submit button
    Then Close application

  @multiple_param
  Scenario: Scenario have parameter
    Given Open facebook application
    When Input to email textbox with "abcd@gmail.com" and password with "123456"
    And Click to Submit button
    Then Close application

  @datatable_step
  Scenario Outline: Create new customer with email<Email>
    Given Open facebook application
    When Input to email and password
      | Email   | password   |
      | <Email> | <password> |
    And Click to Submit button
    Then Verify submitted infor displayed
      | Email   | password   |
      | <Email> | <password> |
    And Close application

    Examples: 
      | Email            | password | City | Address | Phone         |
      | abcd@gmail.com   |   123456 | HP   | TN      | 0101010101010 |
      | hahaha@gmail.com |  2243363 | HP   | AD      | 0101010101010 |
