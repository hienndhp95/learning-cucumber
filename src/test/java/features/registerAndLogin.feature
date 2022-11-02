Feature: Register and Login

@register_login
  Scenario: Register to system and login
  	# Open Url
    Given Open Register page
    When Input to email
    And Click to submit button
    Then Get User and password infor
    When Back to Login page
    And Submit valid infor to login form
    Then Homepage is displayed