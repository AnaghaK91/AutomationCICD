package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
		@FindBy(css=".cartSection h3")
		List<WebElement> cartProducts;
		
		@FindBy(xpath="//li[@class='totalRow']/button")
		WebElement btnCheckout;
		
		public List<WebElement> getCartProductList() {
			
			return cartProducts;
		}
		
		public Boolean VerifyProductMatch(String productName) {
			
			Boolean match = getCartProductList().stream()
					.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
			return match;
		}
		
		public CheckoutPage gotoCheckout() {
			
			btnCheckout.click();
			CheckoutPage checkoutPage = new CheckoutPage(driver);
			return checkoutPage;
			
		}
	
}
