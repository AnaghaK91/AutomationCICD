package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.Testcomponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.OrderConfirmationpage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups={"ErrorHandling"})
	public void LoginErrorvalidation() throws IOException, InterruptedException {

		
		String productName = "ZARA COAT 3";
		String mailid= "anagha.k91@gmail.com";
		String password = "myLearning@2025" ;
		landingPage.loginApplication(mailid, password);
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
	}
	
	

	@Test()
	public void productErrorValidation() throws IOException, InterruptedException {

		
		String productName = "ZARA COAT 3";
		String country = "India";
		String CVVcode = "123";
		String nameOnCard = "Anagha K";
		//String couponCode = "rahulshettyacademy";
		String successmessage = "Thank you for your order";
		String mailid= "bharan2020ismyworld@gmail.com";
		String password = "Bhadran$2020" ;

		ProductCatalogue ProductCatalogue = landingPage.loginApplication(mailid,password);

		List<WebElement> products = ProductCatalogue.getProductList();
		ProductCatalogue.addproductToCart(productName);
		CartPage CartPage = ProductCatalogue.cartNavigation();

		List<WebElement> cartProducts = CartPage.getCartProductList();
		Boolean match = CartPage.VerifyProductMatch("ZARA COAT 33");
		Assert.assertFalse(match);
		

		
	}
	
}
