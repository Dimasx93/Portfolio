#Start date: 17/11/2023
#Beginning Automation UserStory2: As a user I should be able to access and manipulate with account
#Author: Stefano Di Mauro
#TODO TestCase No1
#Enter info on  http://demoshop24.com/ and register a new user
#After check that in MY Account page you will see all the defined buttons:
# My account, Edit account, Password, Wish List, Order History,Downloads,
# Returns, Logout, Newsletter, Reward Points, Recurring payments,Transactions
@userStory2
  @testCase1
  Feature: Test Case no1
    Scenario Outline: I add new user
      Given I am on Demo page
      When I click on Register
      And I am on registration page
      And I enter values:
        | name | <name> |
        | surname |  <surname> |
        | e-mail | <e-mail> |
        | phonenumber |<phonenumber> |
        | password | <password> |
        | confirmpass | <confirmpass> |
      And Click on agree Policy
      And Click on Continue button
      And Click on other continue button
      Then Check all submenu items
      Examples:
        | name | surname | e-mail | phonenumber | password | confirmpass |
        | Stefano | Di Mauro | df@df.df | 0957778 | 0000 | 0000       |
  #Start date: 18/11/2023
  #Beginning Automation UserStory2: As a user I should be able to access and manipulate with account
  #Author: Stefano Di Mauro
  #TODO TestCase No2
  #Click 'Address book' menu item
  #Add new addresses and set one as default        FIND A WAY TO KEEP IT LOGGED IN AFTER TESTCASE1
  @testCase2
    Scenario Outline: I add new addresses
      Given I am in My Account page
      When I login
      And I click on Address Book
      And I click on New Address button
      And Enter values:
        | name | <name> |
        | surname |  <surname> |
        | address | <address> |
        | city |<city> |
      And Click on Country
      And Choose Region State
      And Check Default box
      And Click the Continue button
      Then Check address

      Examples:
        | name | surname | address | city |
        | Stefano | Di Mauro | bamboozled | Licuddia |

  @testCase3
  #'Edit account' menu item contains editable user info:First name, Last name, Email, Phone number
    Scenario Outline: I modify user's data
    Given I am in My Account page
    When I login
    And I click on Edit Account
    And I change values:
      | name | <name> |
      | surname |  <surname> |
      | e-mail | <e-mail> |
      | phonenumber |<phonenumber> |
    Then Click Continue button
    Examples:
      | name | surname | e-mail | phonenumber |
      | Paolo | Maldini | hh@hh.hh | 37055882 |