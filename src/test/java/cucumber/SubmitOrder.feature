@tag
Feature: Purchase the order form Ecommerce Website
  I want to use this template for my feature file

Background:
Given I landed on Ecommerce Page


  @Regression
  Scenario Outline: Positive test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." is displayed on ConfirmationPage

    Examples: 
      | name  								| 	password    | productName|
      | rahulshetty@gmail.com |   Iamking@000 | ZARA COAT 3|  
      
