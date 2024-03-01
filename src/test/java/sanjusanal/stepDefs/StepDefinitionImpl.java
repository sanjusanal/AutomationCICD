package sanjusanal.stepDefs;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sanjusanal.PageObjects.CartPage;
import sanjusanal.PageObjects.CheckoutPage;
import sanjusanal.PageObjects.ConfirmationPage;
import sanjusanal.PageObjects.LandingPage;
import sanjusanal.PageObjects.ProductCatalogue;
import sanjusanal.TestComponents.BaseTest;

public class StepDefinitionImpl extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	ConfirmationPage confirmationPage; 
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage = lauchApplication(); 
	}
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) 
	{
		 productCatalogue =landingpage.loginApplication(username,password );
	}
	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	@And("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName)
	{
        CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.clickCheckout();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}
	@Then("{string} is displayed on ConfirmationPage")
	public void message_displayed_ConfirmationPage(String string) 
	{
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	@Then("{string} message is displayed")
	public void message_is_displayed(String string) 
	{
		Assert.assertEquals(string, landingpage.getErrorMessage());
		driver.close();

	}
	

}

