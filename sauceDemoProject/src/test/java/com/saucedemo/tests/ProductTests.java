package com.saucedemo.tests;

import com.saucedemo.pages.ProductPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTests extends BaseTest {

	@Test
	public void TC_CHK_01_ProductPageShowsNamePriceAndAddToCart() {
		// 1. Go to Catalog
		driver.findElement(By.linkText("Catalog")).click();

		// 2. Click an in-stock product (Grey jacket)
		ProductPage productPage = new ProductPage(driver);
		productPage.clickProductLink("Grey jacket");

		// Expected: product page shows name, price, and an enabled Add to Cart button
		Assert.assertTrue(productPage.areProductElementsVisible(),
				"Name, price and Add to Cart button should all be visible");
		Assert.assertEquals(productPage.getProductName(), "Grey jacket");
		Assert.assertTrue(productPage.getProductPrice().contains("55.00"));
		Assert.assertTrue(productPage.isAddToCartEnabled(), "Add to Cart button should be enabled");
	}
}