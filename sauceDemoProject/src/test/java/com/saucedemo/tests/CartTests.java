package com.saucedemo.tests;

import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.ProductPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTests extends BaseTest {

	private void addProductToCart(String productName) {
		driver.findElement(By.linkText("Catalog")).click();
		pause(1000);

		ProductPage productPage = new ProductPage(driver);
		productPage.clickProductLink(productName);
		pause(1000);

		productPage.clickAddToCart();
		pause(2000);
	}

	private CartPage openCartPage() {
		driver.get("https://sauce-demo.myshopify.com/cart");
		pause(1500);
		return new CartPage(driver);
	}

	@Test
	public void TC_CHK_03_CartShowsProductQuantityAndTotal() {
		addProductToCart("Grey jacket");
		CartPage cartPage = openCartPage();

		String cartText = cartPage.getPageText();
		Assert.assertTrue(cartText.contains("Grey jacket"), "Product name should be listed in the cart");
		Assert.assertTrue(cartText.contains("55.00"), "Price/total (£55.00) should be listed in the cart");
		Assert.assertEquals(cartPage.getQuantityValue(), "1", "Quantity field should equal 1");
	}

	@Test
	public void TC_CHK_04_UpdatingQuantityRecalculatesTotal() {
		addProductToCart("Grey jacket");
		CartPage cartPage = openCartPage();

		cartPage.changeQuantityByLine(1, 3);
		pause(1500);
		driver.navigate().refresh();
		pause(1000);

		Assert.assertEquals(cartPage.getQuantityValue(), "3", "Quantity field should reflect the updated value");
		Assert.assertTrue(cartPage.getPageText().contains("165.00"),
				"Line total and grand total should update to £165.00 (55 x 3)");
	}

	@Test
	public void TC_CHK_05_CartTotalSumsAcrossMultipleProducts() {
		addProductToCart("Grey jacket");
		addProductToCart("Striped top");
		addProductToCart("Striped top");

		CartPage cartPage = openCartPage();
		String cartText = cartPage.getPageText();

		Assert.assertTrue(cartText.contains("55.00"), "Grey jacket line total (£55.00) should be present");
		Assert.assertTrue(cartText.contains("100.00"), "Striped top line total (£100.00) should be present");
		Assert.assertTrue(cartText.contains("155.00"), "Grand total (£155.00) should be present");
	}
}