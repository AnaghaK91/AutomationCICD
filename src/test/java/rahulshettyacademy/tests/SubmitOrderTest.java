package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.AbstractComponents.AbstractComponents;
import rahulshettyacademy.Testcomponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderConfirmationpage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	
	
	String productName = "ZARA COAT 3";
	String mailid= "anagha.k91@gmail.com";
	String password = "MyLearning@2024" ;
	
	@Test(dataProvider="getData",groups="Purchase")
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

		
		
		String successmessage = "THANKYOU FOR THE ORDER.";
		
				

		//LandingPage landingPage = launchApplication();

		ProductCatalogue ProductCatalogue = landingPage.loginApplication(input.get("mailid"),input.get("password"));

		List<WebElement> products = ProductCatalogue.getProductList();
		ProductCatalogue.addproductToCart(input.get("productName"));
		CartPage CartPage = ProductCatalogue.cartNavigation();

		List<WebElement> cartProducts = CartPage.getCartProductList();
		Boolean match = CartPage.VerifyProductMatch(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = CartPage.gotoCheckout();
		checkoutPage.enterUserDetails("India");
		OrderConfirmationpage orderConfirmation = checkoutPage.navigateToOrderConfirmation();
		orderConfirmation.verifyOrderConfirmation(successmessage);
		Assert.assertTrue(true);

		
	}
	
	
	@Test(dependsOnMethods={"submitOrder"})
	public void verifyOrder() {
		
		ProductCatalogue ProductCatalogue = landingPage.loginApplication(mailid,password);
		OrderPage orderPage =  ProductCatalogue.OrderNavigation();
		Boolean match = orderPage.verifyOrderDisplay(productName);
		Assert.assertTrue(match);
	
		
	}
	
	public String getScreenshot(String testCaseName) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+ "//Reports//" + testCaseName + ".png" );
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+ "//Reports//" + testCaseName + ".png";
	}
		
	@DataProvider
	public Object[][] getData() throws IOException {
		
		
		
		List<HashMap<String,String>> data = getJsondataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
	//	System.out.println(data.size());
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	
	}
	

}


/*//	return new Object[][] {{"anagha.k91@gmail.com","MyLearning@2024","ZARA COAT 3"},{"bharan2020ismyworld@gmail.com","Bhadran$2020","ADIDAS ORIGINAL"}};
 * HashMap<String,String> map1 = new HashMap<String,String>();
 * map1.put("mailid", "anagha.k91@gmail.com"); map1.put("password",
 * "MyLearning@2024"); map1.put("productName", "ZARA COAT 3");
 * 
 * HashMap<String,String> map2 = new HashMap<String,String>();
 * map2.put("mailid", "bharan2020ismyworld@gmail.com"); map2.put("password",
 * "Bhadran$2020"); map2.put("productName", "ADIDAS ORIGINAL");
 */
