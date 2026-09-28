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

		Assert.assertTrue(cartPage.getCartText().contains("Grey jacket"));
		Assert.assertTrue(cartPage.getTotal().contains("55.00"));
		Assert.assertEquals(cartPage.getQuantity(), "1");
		Reporter.log("pass", true);
	}

	@Test(priority = 2)
	public void updatingQuantityRecalculatesTotal() {
		CartPage cartPage = new CartPage(driver);
		cartPage.addProductToCart("Grey jacket");
		cartPage.openCart();

		cartPage.setQuantity("3");
		cartPage.clickUpdate();

		Assert.assertEquals(cartPage.getQuantity(), "3");
		Assert.assertTrue(cartPage.getTotal().contains("165.00"));
		Reporter.log("pass", true);
	}

	@Test(priority = 3)
	public void cartTotalSumsMultipleProducts() {
		CartPage cartPage = new CartPage(driver);
		cartPage.addProductToCart("Grey jacket");
		cartPage.addProductToCart("Striped top");
		cartPage.addProductToCart("Striped top");
		cartPage.openCart();

		Assert.assertTrue(cartPage.getCartText().contains("55.00"));
		Assert.assertTrue(cartPage.getCartText().contains("100.00"));
		Assert.assertTrue(cartPage.getTotal().contains("155.00"));
		Reporter.log("pass", true);
	}

	@Test(priority = 4)
	public void emptyCartShowsMessage() {
		CartPage cartPage = new CartPage(driver);
		cartPage.openCart();

		Assert.assertTrue(cartPage.getEmptyCartMessage().contains("It appears that your cart is currently empty"));
		Assert.assertFalse(cartPage.isCheckoutButtonShown());
		Reporter.log("pass", true);
	}

	@Test(priority = 5)
	public void checkoutOpensWithCartProducts() {
		CartPage cartPage = new CartPage(driver);
		cartPage.addProductToCart("Noir jacket");
		cartPage.openCart();
		cartPage.clickCheckout();

		String checkoutText = cartPage.getCheckoutPageText();
		Assert.assertTrue(checkoutText.contains("Noir jacket"));
		Assert.assertTrue(checkoutText.contains("Total"));
		Reporter.log("pass", true);
	}

	@Test(priority = 6)
	public void negativeQuantityIsIgnored() {
		CartPage cartPage = new CartPage(driver);
		cartPage.addProductToCart("Grey jacket");
		cartPage.openCart();
		String quantityBefore = cartPage.getQuantity();

		cartPage.setQuantity("-1");
		cartPage.clickUpdate();

		Assert.assertEquals(cartPage.getQuantity(), quantityBefore);
		Reporter.log("pass", true);
	}

	@Test(priority = 7)
	public void veryLargeQuantityShowsCartError() {
		CartPage cartPage = new CartPage(driver);
		cartPage.addProductToCart("Grey jacket");
		cartPage.openCart();

		cartPage.setQuantity("999999999");
		cartPage.clickUpdate();

		Assert.assertTrue(cartPage.isErrorShown());
		Reporter.log("pass", true);
	}

	@Test(priority = 8)
	public void miniCartShowsEmptyMessage() {
		CartPage cartPage = new CartPage(driver);
		cartPage.openMiniCart();

		Assert.assertTrue(cartPage.getMiniCartEmptyMessage().contains("Your cart is empty."));
		Reporter.log("pass", true);
	}
}