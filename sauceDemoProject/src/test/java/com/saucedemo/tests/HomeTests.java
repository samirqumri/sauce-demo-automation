package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.ProductPage;

public class HomeTests extends BaseTest {

	@Test(priority = 2)
	public void productPageShowsImageDescriptionAndRelatedProducts() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickFeaturedProduct("Grey jacket");

		ProductPage productPage = new ProductPage(driver);
		Assert.assertTrue(productPage.areProductElementsVisible());

		String relatedText = productPage.getRelatedProductsText();
		Assert.assertTrue(relatedText.contains("You Might Also Like"));
		Assert.assertTrue(relatedText.contains("Noir jacket"));
		Assert.assertTrue(relatedText.contains("Striped top"));
	}

	@Test(priority = 3)
	public void relatedProductLinkOpensItsPage() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickFeaturedProduct("Grey jacket");

		ProductPage productPage = new ProductPage(driver);
		productPage.clickRelatedProduct("Striped top");

		Assert.assertEquals(productPage.getProductName(), "Striped top");
	}
}