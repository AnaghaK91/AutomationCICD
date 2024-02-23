package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class OrderConfirmationpage extends AbstractComponents {
	
	WebDriver driver;
	
	public OrderConfirmationpage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//h1[@class='hero-primary']")
	WebElement OrderConfirmationMessage ;
	
	
	public Boolean verifyOrderConfirmation(String successmessage) {
		
	Boolean success = OrderConfirmationMessage.getText().equalsIgnoreCase(successmessage);
	return success;	
		
	}
	
	
	
	
	
}
	

