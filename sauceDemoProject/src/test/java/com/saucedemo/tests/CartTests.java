package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.saucedemo.pages.CartPage;

public class CartTests extends BaseTest {

	@Test(priority = 1)
	public void cartShowsProductAndTotal() {

		CartPage cartPage = new CartPage(driver);

		cartPage.addProductToCart("Grey jacket");
		cartPage.openCart();

		String cartText = cartPage.getPageText();

		Assert.assertTrue(cartText.contains("Grey jacket"));
		Assert.assertTrue(cartText.contains("55.00"));
		Assert.assertEquals(cartPage.getQuantityValue(), "1");
		Reporter.log("pass", true);
	}

	@Test(priority = 2)
	public void updatingQuantityRecalculatesTotal() {

		CartPage cartPage = new CartPage(driver);

		cartPage.addProductToCart("Grey jacket");
		cartPage.openCart();

		cartPage.changeQuantity(3);

		Assert.assertEquals(cartPage.getQuantityValue(), "3");
		Assert.assertTrue(cartPage.getPageText().contains("165.00"));
		Reporter.log("pass", true);
	}

	@Test(priority = 3)
	public void cartTotalSumsMultipleProducts() {

		CartPage cartPage = new CartPage(driver);

		cartPage.addProductToCart("Grey jacket");
		cartPage.addProductToCart("Striped top");
		cartPage.addProductToCart("Striped top");

		cartPage.openCart();

		String cartText = cartPage.getPageText();

		Assert.assertTrue(cartText.contains("55.00"));
		Assert.assertTrue(cartText.contains("100.00"));
		Assert.assertTrue(cartText.contains("155.00"));
		Reporter.log("pass", true);
	}

	@Test(priority = 4)
	public void emptyCartShowsMessage() {

		CartPage cartPage = new CartPage(driver);

		cartPage.openCart();

		String message = cartPage.getEmptyCartMessage();

		Assert.assertTrue(message.contains("It appears that your cart is currently empty"));
		Assert.assertEquals(cartPage.getCheckoutButtonsCount(), 0);
		Reporter.log("pass", true);
	}

	@Test(priority = 5)
	public void checkoutOpensWithCartProducts() {

		CartPage cartPage = new CartPage(driver);

		cartPage.addProductToCart("Noir jacket");
		cartPage.openCart();
		cartPage.clickCheckout();

		String summaryText = cartPage.getCheckoutSummaryText();

		Assert.assertTrue(summaryText.contains("Noir jacket"));
		Assert.assertTrue(summaryText.contains("Total"));
		Reporter.log("pass", true);
	}

	@Test(priority = 6)
	public void negativeQuantityIsIgnored() {

		CartPage cartPage = new CartPage(driver);

		cartPage.addProductToCart("Grey jacket");
		cartPage.openCart();

		String quantityBeforeUpdate = cartPage.getQuantityValue();

		cartPage.setQuantityValue("-1");
		cartPage.clickUpdateButtonAndWaitForReload();

		String quantityAfterUpdate = cartPage.getQuantityValue();

		Assert.assertEquals(quantityAfterUpdate, quantityBeforeUpdate);
		Reporter.log("pass", true);
	}

	@Test(priority = 7)
	public void veryLargeQuantityShowsCartError() {

		CartPage cartPage = new CartPage(driver);

		cartPage.addProductToCart("Grey jacket");
		cartPage.openCart();

		cartPage.setQuantityValue("999999999");
		cartPage.clickUpdateButtonAndWaitForReload();

		Assert.assertTrue(cartPage.isCartErrorPageShown());
		Reporter.log("pass", true);
	}

	@Test(priority = 8)
	public void miniCartShowsEmptyMessage() {

		CartPage cartPage = new CartPage(driver);

		cartPage.clickMiniCartToggle();

		Assert.assertTrue(cartPage.getMiniCartEmptyMessage().contains("Your cart is empty."));
		Reporter.log("pass", true);
	}
}