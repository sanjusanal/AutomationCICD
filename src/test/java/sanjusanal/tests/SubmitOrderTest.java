package sanjusanal.tests;

import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import sanjusanal.PageObjects.LandingPage;
import sanjusanal.PageObjects.OrderPage;
import sanjusanal.PageObjects.ProductCatalogue;
import sanjusanal.TestComponents.BaseTest;
import sanjusanal.PageObjects.CartPage;
import sanjusanal.PageObjects.CheckoutPage;
import sanjusanal.PageObjects.ConfirmationPage;

 

public class SubmitOrderTest extends BaseTest{
	String productName ="ZARA COAT 3";
	
    @Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {
		
		
		ProductCatalogue productCatalogue =landingpage.loginApplication(input.get("email"), input.get("pwd"));
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.clickCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		

	}
    @Test(dependsOnMethods= {"submitOrder"})
    public void OrderHistoryTest() {
    	
    	ProductCatalogue productCatalogue =landingpage.loginApplication("sanjuselenium@gmail.com", "Password@1");
    	OrderPage ordersPage = productCatalogue.goToOrderPage();
    	
    	Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
    	
    	
    }
   
    
    
   
    
    @DataProvider
      
    public Object[][] getData() throws IOException {
    	
//    	HashMap<String,String> map = new HashMap<String,String>();
//    	map.put("email", "sanjuselenium@gmail.com");
//    	map.put("pwd", "Password@1");	
//    	map.put("productName", "ZARA COAT 3");
//    	
//    	HashMap<String,String> map1 = new HashMap<String,String>();
//    	map1.put("email", "rahulshetty@gmail.com");
//    	map1.put("pwd", "Iamking@000");
//    	map1.put("productName", "IPHONE 13 PRO");
    	List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//sanjusanal//data//PurchaseOrder.json");
    	
    	return new Object[][] {{data.get(0)},{data.get(1)}};
    }

}
