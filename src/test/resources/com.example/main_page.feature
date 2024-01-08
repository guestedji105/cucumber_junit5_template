Feature: Main page functionality

  @positive
  Scenario: User adds one product to the cart
    Given user enter login page
    When user enters valid credentials
    And clicks login button
    Then main page opens
    When user adds first product to the cart
    Then amount of products in the cart is 1

  @positive
  Scenario: User adds two products to the cart
    Given user enter login page
    When user enters valid credentials
    And clicks login button
    Then main page opens
    When user adds first and last products to the cart
    Then amount of products in the cart is 2

  @positive
  Scenario: User adds all products to the cart
    Given user enter login page
    When user enters valid credentials
    And clicks login button
    Then main page opens
    When user adds all products to the cart
    Then amount of products in the cart is 6