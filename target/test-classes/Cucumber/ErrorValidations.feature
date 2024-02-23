
@tag
Feature: Purchase order from ecommerce website


  @ErrorValidation
  Scenario Outline: Login Error Validations
  
    Given I landed on ecommerce page
    And I logged in with <username> and <password>
    Then "Incorrect email or password." message is displayed
    

    Examples: 
      | username             | password          |
      | anagha.k91@gmail.com | MyLearning@2025   |
      

      
      
   @ErrorValidation
  Scenario Outline: Verify product in cart page
  
    Given I landed on ecommerce page
    And I logged in with <username> and <password>
    When I add product <productName> to cart
    Then product "ZARA COT 33" not displayed in cart
    

    Examples: 
      | username             | password          |productName    |
      | anagha.k91@gmail.com | MyLearning@2024   |ZARA COAT 3    |   
      
  
      
      
      
      