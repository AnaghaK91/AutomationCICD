package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features= "src/test/java/Cucumber" ,glue="rahulshettyacademy.stepdefenition" , monochrome=true ,
		tags= "@Regression" , plugin= {"pretty","html:target/cucumber-reports"}
		)
public class TestNGTestRunner extends AbstractTestNGCucumberTests{

	
}
