package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.ProductPage;

public class ProductTests extends BaseTest {

	@Test(priority = 1)
	public void productPageShowsNameAndPrice() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Catalog");
		Thread.sleep(1000);

		ProductPage productPage = new ProductPage(driver);
		productPage.clickProductLink("Grey jacket");
		Thread.sleep(1000);

		Assert.assertTrue(productPage.areProductElementsVisible());
		Assert.assertEquals(productPage.getProductName(), "Grey jacket");
		Assert.assertTrue(productPage.getProductPrice().contains("55.00"));
		Assert.assertTrue(productPage.isAddToCartEnabled());
	}

	@Test(priority = 2)
	public void addToCartIncreasesCartCount() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Catalog");
		Thread.sleep(1000);

		ProductPage productPage = new ProductPage(driver);
		productPage.clickProductLink("Grey jacket");
		Thread.sleep(1000);

		CartPage cartPage = new CartPage(driver);
		int countBefore = cartPage.getCartCount();

		productPage.clickAddToCart();
		Thread.sleep(4000);

		int countAfter = cartPage.getCartCount();
		Assert.assertEquals(countAfter, countBefore + 1);
	}

	@Test(priority = 3)
	public void soldOutProductCannotBeAdded() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Catalog");
		Thread.sleep(1000);

		ProductPage productPage = new ProductPage(driver);
		productPage.clickProductLink("Brown Shades");
		Thread.sleep(1000);

		CartPage cartPage = new CartPage(driver);
		int countBefore = cartPage.getCartCount();

		Assert.assertTrue(productPage.isProductSoldOut());

		int countAfter = cartPage.getCartCount();
		Assert.assertEquals(countAfter, countBefore);
	}
}