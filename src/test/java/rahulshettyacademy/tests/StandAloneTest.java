package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage(	).deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		String productName = "ZARA COAT 3";
		driver.get("https://rahulshettyacademy.com/client");
		
	//	LandingPage landingPage = new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("anagha.k91@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("MyLearning@2024");
		driver.findElement(By.id("login")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
		List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		// Assert.assertTrue(true);
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.xpath("//li[@class='totalRow']/button")).click();
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("india");
		//Actions a = new Actions(driver);
		//a.click();
		//a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "india");
		
		List<WebElement> countryList = driver.findElements(By.xpath("//span[@class='ng-star-inserted']"));
		for(WebElement country : countryList) {
			if(country.getText().equalsIgnoreCase("india")) {
				country.click();
			}
			
		}
		//Select selectMonth = new Select(driver.findElement(By.xpath("(//select[@class='input ddl'])[1]")));
		//selectMonth.selectByVisibleText("6");
		//Select selectdate = new Select(driver.findElement(By.xpath("(//select[@class='input ddl'])[2]")));
		//selectdate.deselectByVisibleText("31");
		driver.findElement(By.xpath("//div[@class='field small']//div[contains(text(),'CVV Code')]/following-sibling::input")).sendKeys("123");
		driver.findElement(By.xpath("//div[contains(text(),'Name on Card')]/following-sibling::input")).sendKeys("Anagha K");
		driver.findElement(By.xpath("//input[@name='coupon']")).sendKeys("rahulshettyacademy");
		
		w.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Apply Coupon')]")));
		//driver.findElement(By.xpath("//button[contains(text(),'Apply Coupon')]")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btnn")));
		
		
		
		driver.findElement(By.cssSelector(".btnn")).click(); 
		
		Boolean successmessage = driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText().equalsIgnoreCase("Thanks you for the order");
		Assert.assertTrue(true);
		driver.close();
	}

}
