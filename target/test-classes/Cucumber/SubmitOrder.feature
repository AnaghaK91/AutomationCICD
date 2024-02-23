
@tag
Feature: Purchase order for ecommerce website

Background: 
Given I landed on ecommerce page


  @Regression
  Scenario Outline: Positive test of purchasing the order
  
    Given I logged in with <username> and <password>
    When I add product <productName> to cart
    And checkout  <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation message

    Examples: 
      | username                 | password           | productName  |
      | anagha.k91@gmail.com     | MyLearning@2024    | ZARA COAT 3   |
     

     
     
 
      