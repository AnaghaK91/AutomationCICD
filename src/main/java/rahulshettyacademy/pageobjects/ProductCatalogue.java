package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);	
	}
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	//WebElement userPassword = driver.findElement(By.id("userPassword"));
	//WebElement LoginBtn = driver.findElement(By.id("login"));
	
	//PageFactory
	@FindBy(css=".col-lg-4")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner ;
	
	@FindBy(css="[routerlink*='cart']")
	WebElement carticon;
	
	By productBy = By.cssSelector(".col-lg-4");
	By addtocart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	
	public List<WebElement> getProductList() {
		
		 waitForElementToAppear(productBy);
		 return products;
		
	}
	public WebElement getProductName(String productName) {
		
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
		
	}
	
	public void addproductToCart(String productName) throws InterruptedException {
		
		WebElement prod = getProductName(productName);
		 prod.findElement(addtocart).click();
		 waitForElementToAppear(toastMessage);
		 waitForElementToDisappear(spinner);
	}
	

	
	
}
