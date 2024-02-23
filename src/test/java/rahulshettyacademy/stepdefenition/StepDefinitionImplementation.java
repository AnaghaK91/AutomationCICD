package rahulshettyacademy.stepdefenition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.Testcomponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderConfirmationpage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

  

public class StepDefinitionImplementation extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue ProductCatalogue ; 
	public CartPage CartPage;
	public CheckoutPage checkoutPage;
	public OrderConfirmationpage orderConfirmation;
	
	@Given("I landed on ecommerce page")
	public void i_landed_on_ecommerce_page() throws IOException {
		
		landingPage = launchApplication();
	}

	@Given("^I logged in with (.+) and (.+)$")
	public void i_logged_in_with_anagha_k91_gmail_com_and_my_learning(String mailid, String password) {
		
		 ProductCatalogue = landingPage.loginApplication(mailid,password);
	}

	@When("^I add product (.+) to cart$")
	public void i_add_product__cart(String productName) throws InterruptedException {
		
		List<WebElement> products = ProductCatalogue.getProductList();
		ProductCatalogue.addproductToCart(productName);
		CartPage = ProductCatalogue.cartNavigation();
		

		
	}

	@When("^checkout  (.+) and submit the order$")
	public void checkout_zara_cot_and_submit_the_order(String productName) throws InterruptedException {
		
		List<WebElement> cartProducts = CartPage.getCartProductList();
		Boolean match = CartPage.VerifyProductMatch(productName);
		Assert.assertTrue(match);
		checkoutPage = CartPage.gotoCheckout();
		checkoutPage.enterUserDetails("India");
		orderConfirmation = checkoutPage.navigateToOrderConfirmation();
	}

	@Then("{string} message is displayed on confirmation message")
	public void message_is_displayed_on_confirmation_message(String successmessage) {
		
		Boolean message = orderConfirmation.verifyOrderConfirmation(successmessage);
		Assert.assertTrue(message);
		driver.close();	
		
		
	}
	
	@Then("^\"([^\"]*)\" message is displayed")
	public void message_is_displayed(String invalidLoginMsg) {
		
		Assert.assertEquals(invalidLoginMsg, landingPage.getErrorMessage());
		
	    driver.close();
	    
	}
	
	
	@Then("product {string} not displayed in cart")
	public void product_not_displayed_in_cart(String wrongProduct) {
		Boolean match = CartPage.VerifyProductMatch(wrongProduct);
		Assert.assertFalse(match);
		driver.close(); 
       
}


	
	


}
