package sanjusanal.tests;

import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import org.testng.AssertJUnit;
import org.testng.IRetryAnalyzer;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import sanjusanal.PageObjects.CartPage;
import sanjusanal.PageObjects.ProductCatalogue;
import sanjusanal.TestComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=sanjusanal.TestComponents.Retry.class)
	public void LoginErrorValidation() throws InterruptedException, IOException {

		landingpage.loginApplication("juselenium@gmail.com", "Paword@1");

		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());

	}

	@Test
	public void ProductErrorValidation() throws InterruptedException {
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingpage.loginApplication("rahulshetty@gmail.com", "Iamking@000");

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);

		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 3");
		Assert.assertTrue(match);
	}

}
