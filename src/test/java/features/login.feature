@parameter
Feature: Parameter Feature


  @no_param
  Scenario: Scenario have no parameter
    When Input to email textbox
    And Input password textbox
    And Click to Submit button

  @param_mark
  Scenario: Scenario have parameter
    When Input to email textbox with "abcd@gmail.com"
    And Input password textbox with "123456"
    And Click to Submit button

  @param_no_mark
  Scenario: Scenario have parameter
    When Input to email textbox with abcd@gmail.com
    And Input password textbox with 123456
    And Click to Submit button

  @multiple_param
  Scenario: Scenario have parameter
    When Input to email textbox with "abcd@gmail.com" and password with "123456"
    And Click to Submit button

  @datatable_step
  Scenario Outline: Create new customer with email<Email>
    When Input to email and password
      | Email   | password   |
      #| <Email> | <password> |
      | autotest01@gmail.com |123456 |
      | autotest02@gmail.com |123456 |
      | autotest03@gmail.com |123456 |
    And Click to Submit button
    #Then Verify submitted infor displayed
      #| Email   | password   |
      #| <Email> | <password> |

    Examples: 
      | Email            | password | City | Address | Phone         |
      | abcd@gmail.com   |   123456 | HP   | TN      | 0101010101010 |

  @datatable_scenario @data_driven_testing
  Scenario Outline: Data Table in Scenario
    When Input to email textbox with <Email>
    And Input password textbox with <password>
    And Click to Submit button

    Examples: 
      | Email                    | password   |
      | abcd@gmail.com           |     123456 |
      | hahaha@gmail.com         |    2243363 |
      | cbcbcbab@gmail.com       | 1212121212 |
      | rqwrqwrqwr@gmail.com     | 1212121212 |
      | tewtw@gmail.com          | 1212121212 |
      | cbcbcghgjfgbab@gmail.com | 1212121212 |
