package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.ProductPage;

public class CartTests extends BaseTest {

	@Test(priority = 1)
	public void cartShowsProductAndTotal() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Catalog");
		Thread.sleep(1000);

		ProductPage productPage = new ProductPage(driver);
		productPage.clickProductLink("Grey jacket");
		Thread.sleep(1000);
		productPage.clickAddToCart();
		Thread.sleep(2000);

		driver.get("https://sauce-demo.myshopify.com/cart");
		Thread.sleep(1500);

		CartPage cartPage = new CartPage(driver);
		String cartText = cartPage.getPageText();

		Assert.assertTrue(cartText.contains("Grey jacket"));
		Assert.assertTrue(cartText.contains("55.00"));
		Assert.assertEquals(cartPage.getQuantityValue(), "1");
		Reporter.log("pass",true);
	}

	@Test(priority = 2)
	public void updatingQuantityRecalculatesTotal() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Catalog");
		Thread.sleep(1000);

		ProductPage productPage = new ProductPage(driver);
		productPage.clickProductLink("Grey jacket");
		Thread.sleep(1000);
		productPage.clickAddToCart();
		Thread.sleep(2000);

		driver.get("https://sauce-demo.myshopify.com/cart");
		Thread.sleep(1500);

		CartPage cartPage = new CartPage(driver);
		cartPage.changeQuantityByLine(1, 3);
		Thread.sleep(1500);
		driver.navigate().refresh();
		Thread.sleep(1000);

		Assert.assertEquals(cartPage.getQuantityValue(), "3");
		Assert.assertTrue(cartPage.getPageText().contains("165.00"));
		Reporter.log("pass",true);
	}

	@Test(priority = 3)
	public void cartTotalSumsMultipleProducts() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		ProductPage productPage = new ProductPage(driver);

		homePage.clickMenuLink("Catalog");
		Thread.sleep(1000);
		productPage.clickProductLink("Grey jacket");
		Thread.sleep(1000);
		productPage.clickAddToCart();
		Thread.sleep(2000);

		homePage.clickMenuLink("Catalog");
		Thread.sleep(1000);
		productPage.clickProductLink("Striped top");
		Thread.sleep(1000);
		productPage.clickAddToCart();
		Thread.sleep(2000);

		homePage.clickMenuLink("Catalog");
		Thread.sleep(1000);
		productPage.clickProductLink("Striped top");
		Thread.sleep(1000);
		productPage.clickAddToCart();
		Thread.sleep(2000);

		driver.get("https://sauce-demo.myshopify.com/cart");
		Thread.sleep(1500);

		CartPage cartPage = new CartPage(driver);
		String cartText = cartPage.getPageText();

		Assert.assertTrue(cartText.contains("55.00"));
		Assert.assertTrue(cartText.contains("100.00"));
		Assert.assertTrue(cartText.contains("155.00"));
		Reporter.log("pass",true);
	}

	@Test(priority = 4)
	public void emptyCartShowsMessage() throws InterruptedException {
		driver.get("https://sauce-demo.myshopify.com/cart");
		Thread.sleep(1500);

		CartPage cartPage = new CartPage(driver);
		String message = cartPage.getEmptyCartMessage();

		Assert.assertTrue(message.contains("It appears that your cart is currently empty"));
		Assert.assertEquals(cartPage.getCheckoutButtonsCount(), 0);
		Reporter.log("pass",true);
	}

	@Test(priority = 5)
	public void checkoutOpensWithCartProducts() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Catalog");
		Thread.sleep(1000);

		ProductPage productPage = new ProductPage(driver);
		productPage.clickProductLink("Noir jacket");
		Thread.sleep(1000);
		productPage.clickAddToCart();
		Thread.sleep(2000);

		driver.get("https://sauce-demo.myshopify.com/cart");
		Thread.sleep(1500);

		CartPage cartPage = new CartPage(driver);
		cartPage.clickCheckout();
		Thread.sleep(3000);

		CheckoutPage checkoutPage = new CheckoutPage(driver);
		String summaryText = checkoutPage.getOrderSummaryText();

		Assert.assertTrue(summaryText.contains("Noir jacket"));
		Assert.assertTrue(summaryText.contains("Total"));
		Reporter.log("pass",true);
	}

	@Test(priority = 6)
	public void negativeQuantityIsIgnored() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Catalog");
		Thread.sleep(1000);

		ProductPage productPage = new ProductPage(driver);
		productPage.clickProductLink("Grey jacket");
		Thread.sleep(1000);
		productPage.clickAddToCart();
		Thread.sleep(2000);

		driver.get("https://sauce-demo.myshopify.com/cart");
		Thread.sleep(1500);

		CartPage cartPage = new CartPage(driver);
		String quantityBeforeUpdate = cartPage.getQuantityValue();

		cartPage.setQuantityValue("-1");
		cartPage.clickUpdateButton();
		Thread.sleep(2000);

		String quantityAfterUpdate = cartPage.getQuantityValue();
		Assert.assertEquals(quantityAfterUpdate, quantityBeforeUpdate);
		Reporter.log("pass",true);
	}

	@Test(priority = 7)
	public void veryLargeQuantityShowsCartError() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Catalog");
		Thread.sleep(1000);

		ProductPage productPage = new ProductPage(driver);
		productPage.clickProductLink("Grey jacket");
		Thread.sleep(1000);
		productPage.clickAddToCart();
		Thread.sleep(2000);

		driver.get("https://sauce-demo.myshopify.com/cart");
		Thread.sleep(1500);

		CartPage cartPage = new CartPage(driver);
		cartPage.setQuantityValue("999999999");
		cartPage.clickUpdateButton();
		Thread.sleep(2000);

		Assert.assertTrue(cartPage.isCartErrorPageShown());
		Reporter.log("pass",true);
	}
	@Test(priority = 8)
	public void miniCartShowsEmptyMessage() throws InterruptedException {
		CartPage cartPage = new CartPage(driver);
		cartPage.clickMiniCartToggle();
		Thread.sleep(1000);

		Assert.assertTrue(cartPage.getMiniCartEmptyMessage().contains("Your cart is empty."));
	}
	
}