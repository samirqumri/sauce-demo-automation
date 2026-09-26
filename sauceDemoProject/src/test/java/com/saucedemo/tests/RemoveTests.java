package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.ProductPage;

public class RemoveTests extends BaseTest {

	@Test(priority = 1)
	public void emptyCartHasNoCheckout() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Catalog");
		Thread.sleep(1000);

		homePage.clickMenuLink("Check Out");
		Thread.sleep(2000);

		String currentUrl = driver.getCurrentUrl();
		Assert.assertFalse(currentUrl.contains("checkouts"));
	}

	@Test(priority = 2)
	public void removeOnlyItem() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Catalog");
		Thread.sleep(1000);

		ProductPage productPage = new ProductPage(driver);
		productPage.clickProductLink("Grey jacket");
		Thread.sleep(1000);
		productPage.clickAddToCart();
		Thread.sleep(2000);

		homePage.clickMenuLink("Check Out");
		Thread.sleep(3000); // Gives the platform gateway ample time to process the URL redirect path

		CheckoutPage checkoutpage = new CheckoutPage(driver);
		checkoutpage.ToRemoveOrder();
		Thread.sleep(2000);
		Assert.assertEquals(checkoutpage.getOrderItemsCount(), 0);
	}
}
