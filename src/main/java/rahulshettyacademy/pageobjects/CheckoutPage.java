package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement countryName;
	@FindBy(xpath="//span[@class='ng-star-inserted']")
	public List<WebElement> countryList;
	
	@FindBy(xpath="//div[@class='field small']//div[contains(text(),'CVV Code')]/following-sibling::input")
	WebElement CVVcode;
	
	@FindBy(xpath="//div[contains(text(),'Name on Card')]/following-sibling::input")
	WebElement nameOnCard;
	
	@FindBy(xpath="//input[@name='coupon']")
	WebElement couponCode;
	
	@FindBy(css=".btnn")
	WebElement btnPlaceOrder;
	
	@FindBy(xpath="//button[contains(text(),'Apply Coupon')]")
	WebElement applyCoupon;
	
	By placeOrderBtn = By.cssSelector(".btnn");
	
	
	
	public void enterUserDetails(String country) {
		
		countryName.sendKeys(country);
		
		
		for(WebElement country1 : countryList) {
			if(country1.getText().equalsIgnoreCase(country)) {
				country1.click();
			}
		}
		//	CVVcode.sendKeys(CVV);
		//	nameOnCard.sendKeys(name);
		//	couponCode.sendKeys("rahulshettyacademy");
		//	applyCoupon.click();
			
			 
	}
	
	
	
	public OrderConfirmationpage navigateToOrderConfirmation() throws InterruptedException {
		
		Thread.sleep(5000);
		
		//waitForElementToAppear(placeOrderBtn);
		btnPlaceOrder.click();
		OrderConfirmationpage orderConfirmation = new OrderConfirmationpage(driver);
		return orderConfirmation;
		
		
	}
}
	

