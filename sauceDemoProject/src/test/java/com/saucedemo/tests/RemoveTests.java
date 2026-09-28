package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.ProductPage;

public class RemoveTests extends BaseTest {

	@Test(priority = 1)
	public void emptyCartHasNoCheckout() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Catalog");

		homePage.clickMenuLink("Check Out");

		String currentUrl = driver.getCurrentUrl();
		Assert.assertFalse(currentUrl.contains("checkouts"));
		Reporter.log("pass", true);
	}

	@Test(priority = 2)
	public void removeOnlyItem() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Catalog");

		ProductPage productPage = new ProductPage(driver);
		productPage.clickProductLink("Grey jacket");
		productPage.clickAddToCart();
		Thread.sleep(2000);
		homePage.clickMenuLink("Check Out");

		CheckoutPage checkoutpage = new CheckoutPage(driver);
		checkoutpage.ToRemoveOrder();
		Assert.assertEquals(checkoutpage.getOrderItemsCount(), 0);
		Reporter.log("pass", true);
	}

	@Test(priority = 3)
	public void removeAllProductsOneByOne() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		ProductPage productPage = new ProductPage(driver);
		CheckoutPage checkoutpage = new CheckoutPage(driver);

		homePage.clickMenuLink("Catalog");

		productPage.clickProductLink("Grey jacket");

		productPage.clickAddToCart();
		Thread.sleep(2000);
		homePage.clickMenuLink("Catalog");

		productPage.clickProductLink("Noir jacket");

		productPage.clickAddToCart();
		Thread.sleep(2000);
		homePage.clickMenuLink("Catalog");
		productPage.clickProductLink("Striped top");
		productPage.clickAddToCart();
		Thread.sleep(2000);
		homePage.clickMenuLink("Check Out");

		driver.navigate().refresh();

		checkoutpage.ToRemoveOrder();
		driver.navigate().refresh();

		checkoutpage.ToRemoveOrder();
		driver.navigate().refresh();

		checkoutpage.ToRemoveOrder();
		driver.navigate().refresh();
		Reporter.log("pass", true);
	}

	@Test(priority = 4)
	public void removeWithQuantityThree() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Catalog");

		ProductPage productPage = new ProductPage(driver);
		productPage.clickProductLink("Grey jacket");

		productPage.clickAddToCart();
		Thread.sleep(2000);
		productPage.clickAddToCart();
		Thread.sleep(2000);
		productPage.clickAddToCart();
		Thread.sleep(2000);
		homePage.clickMenuLink("Check Out");

		CheckoutPage checkoutpage = new CheckoutPage(driver);
		checkoutpage.ToRemoveOrder();
		Assert.assertEquals(checkoutpage.getOrderItemsCount(), 0);
		Reporter.log("pass", true);
	}

	@Test(priority = 5)
	public void removedProductCanBeAddedAgain() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Catalog");

		ProductPage productPage = new ProductPage(driver);
		productPage.clickProductLink("Grey jacket");
		productPage.clickAddToCart();
		Thread.sleep(2000);
		homePage.clickMenuLink("Check Out");

		CheckoutPage checkoutpage = new CheckoutPage(driver);
		checkoutpage.ToRemoveOrder();
		Assert.assertEquals(checkoutpage.getOrderItemsCount(), 0);

		homePage.clickMenuLink("Catalog");
		productPage.clickProductLink("Grey jacket");
		productPage.clickAddToCart();
		Thread.sleep(2000);
		homePage.clickMenuLink("Check Out");
		Reporter.log("pass", true);
	}
}
