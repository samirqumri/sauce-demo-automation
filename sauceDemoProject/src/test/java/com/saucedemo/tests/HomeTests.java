package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.ProductPage;

public class HomeTests extends BaseTest {

	@Test(priority = 1)
	public void homePageShowsContent() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		

		String pageText = homePage.getPageText();

		Assert.assertTrue(pageText.contains("Just a demo site showing off what Sauce can do."));
		Assert.assertTrue(pageText.contains("Grey jacket"));
		Assert.assertTrue(pageText.contains("55.00"));
		Assert.assertTrue(pageText.contains("Noir jacket"));
		Assert.assertTrue(pageText.contains("60.00"));
		Assert.assertTrue(pageText.contains("Striped top"));
		Assert.assertTrue(pageText.contains("50.00"));
	}


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