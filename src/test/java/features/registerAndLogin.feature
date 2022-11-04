@customer
Feature: Register and Login and New Customer

  @register_login
  Scenario: Register to system and login
    Given Get login page Url
    When Open Register page
    And Input to email with ""
    And Click to submit button
    Then Get User and password infor
    When Back to Login page
    And Submit valid infor to login form
    Then Homepage is displayed

  @new_customer
  Scenario Outline: Create new customer
    Given Open "New Customer" page
    When Input to "Customer Name" textbox with value "<CustomerName>"
    And Click to "f" radio button
    And Input to "Date of Birth" textbox with value "<DobIn>"
    And Input to "Address" textarea with value "<Address>"
    And Input to "City" textbox with value "<City>"
    And Input to "State" textbox with value "<State>"
    And Input to "PIN" textbox with value "<Pin>"
    And Input to "Mobile Number" textbox with value "<Phone>"
    And Input to "E-mail" textbox with value "<Email>"
    And Input to "Password" textbox with value "<Password>"
    And Click to "Submit" button
    #And Close alert popup
    Then Success message displayed with "Customer Registered Successfully!!!"
    And The valid text displayed at "Customer Name" with value "<CustomerName>"
    And The valid text displayed at "Gender" with value "<Gender>"
    And The valid text displayed at "Birthdate" with value "<DobOut>"
    And The valid text displayed at "Address" with value "<Address>"
    And The valid text displayed at "City" with value "<City>"
    And The valid text displayed at "State" with value "<State>"
    And The valid text displayed at "Pin" with value "<Pin>"
    And The valid text displayed at "Mobile No." with value "<Phone>"
    And The valid text displayed at "Email" with value "<Email>"

    Examples: 
      | CustomerName  | DobIn      | DobOut     | Gender | Address     | City      | State | Pin    | Phone          | Email              | Password |
      | Jack Wilshere | 09/13/1990 | 1990-09-13 | female   | Thuy Nguyen | Hai Phong | VN    | 123456 |  0981818181818 | abdctu2tưw22etwcsc@gmail.com   |    12345 |
      | Jack Gealish  | 02/13/1997 | 1997-02-13 | female | ABCB        | London    | UK    | 123456 | 53251818181818 | utwe22ưt2wetwetw@gmail.com |    12345 |
