package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.ProductPage;

public class ProductTests extends BaseTest {

	@Test(priority = 1)
	public void productPageShowsNameAndPrice() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Catalog");

		ProductPage productPage = new ProductPage(driver);
		productPage.clickProductLink("Grey jacket");

		Assert.assertTrue(productPage.areProductElementsVisible());
		Assert.assertEquals(productPage.getProductName(), "Grey jacket");
		Assert.assertTrue(productPage.getProductPrice().contains("55.00"));
		Assert.assertTrue(productPage.isAddToCartEnabled());
		Reporter.log("pass",true);
	}

	@Test(priority = 2)
	public void addToCartIncreasesCartCount() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Catalog");

		ProductPage productPage = new ProductPage(driver);
		productPage.clickProductLink("Grey jacket");

		CartPage cartPage = new CartPage(driver);
		int countBefore = cartPage.getCartCount();

		productPage.clickAddToCart();

		int countAfter = cartPage.getCartCount();
		Assert.assertEquals(countAfter, countBefore + 1);
		Reporter.log("pass",true);
	}

	@Test(priority = 3)
	public void soldOutProductCannotBeAdded() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Catalog");

		ProductPage productPage = new ProductPage(driver);
		productPage.clickProductLink("Brown Shades");

		CartPage cartPage = new CartPage(driver);
		int countBefore = cartPage.getCartCount();

		Assert.assertTrue(productPage.isProductSoldOut());

		int countAfter = cartPage.getCartCount();
		Assert.assertEquals(countAfter, countBefore);
	}
}