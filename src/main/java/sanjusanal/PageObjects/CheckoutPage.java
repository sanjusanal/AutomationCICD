package sanjusanal.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import sanjusanal.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;
	public CheckoutPage(WebDriver driver2) {
		super(driver2);
		this.driver=driver2;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".action__submit")
	WebElement Submit;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')]) [2]")
	WebElement selectCountry;
	
	By results = By.xpath("//section[contains(@class,'ta-results')]");
	
	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys( country, countryName).build().perform();
		
		waitForElementToAppear(results);
		selectCountry.click();
		
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
	}
	public ConfirmationPage submitOrder() {
		Submit.click();
		 return new ConfirmationPage(driver);
	}
	

	
	

	
	

}
