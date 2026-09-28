package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.saucedemo.pages.ProductPage;

public class ProductTests extends BaseTest {

	@Test(priority = 1)
	public void productPageShowsNameAndPrice() {
		ProductPage productPage = new ProductPage(driver);
		productPage.clickMenuLink("Catalog");
		productPage.clickProductLink("Grey jacket");

		Assert.assertTrue(productPage.areProductElementsVisible());
		Assert.assertEquals(productPage.getProductName(), "Grey jacket");
		Assert.assertTrue(productPage.getProductPrice().contains("55.00"));
		Assert.assertTrue(productPage.isAddToCartEnabled());
		Reporter.log("pass", true);
	}

	@Test(priority = 2)
	public void addToCartIncreasesCartCount() {
		ProductPage productPage = new ProductPage(driver);
		productPage.clickMenuLink("Catalog");
		productPage.clickProductLink("Grey jacket");

		int countBefore = productPage.getCartCount();

		productPage.clickAddToCart();

		int countAfter = productPage.getCartCount();
		Assert.assertEquals(countAfter, countBefore + 1);
		Reporter.log("pass", true);
	}

	@Test(priority = 3)
	public void soldOutProductCannotBeAdded() {
		ProductPage productPage = new ProductPage(driver);
		productPage.clickMenuLink("Catalog");
		productPage.clickProductLink("Brown Shades");

		int countBefore = productPage.getCartCount();

		Assert.assertTrue(productPage.isProductSoldOut());

		int countAfter = productPage.getCartCount();
		Assert.assertEquals(countAfter, countBefore);
	}
}