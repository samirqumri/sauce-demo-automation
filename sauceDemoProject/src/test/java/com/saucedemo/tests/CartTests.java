package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.saucedemo.pages.CartPage;

public class CartTests extends BaseTest {

	@DataProvider
	public Object[][] productData() {
		return new Object[][] {
				{ "Grey jacket" }
		};
	}

	@DataProvider
	public Object[][] multipleProducts() {
		return new Object[][] {
				{ "Grey jacket", "Striped top", "55.00", "100.00", "155.00" }
		};
	}

	@Test(priority = 1, dataProvider = "productData")
	public void cartShowsProductAndTotal(String product) {
		CartPage cartPage = new CartPage(driver);
		cartPage.addProductToCart(product);
		cartPage.openCart();

		Assert.assertTrue(cartPage.getCartText().contains(product));
		Assert.assertTrue(cartPage.getTotal().contains("55.00"));
		Assert.assertEquals(cartPage.getQuantity(), "1");
		Reporter.log("pass", true);
	}

	@Test(priority = 2, dataProvider = "productData")
	public void updatingQuantityRecalculatesTotal(String product) {
		CartPage cartPage = new CartPage(driver);
		cartPage.addProductToCart(product);
		cartPage.openCart();

		cartPage.setQuantity("3");
		cartPage.clickUpdate();

		Assert.assertEquals(cartPage.getQuantity(), "3");
		Assert.assertTrue(cartPage.getTotal().contains("165.00"));
		Reporter.log("pass", true);
	}

	@Test(priority = 3, dataProvider = "multipleProducts")
	public void cartTotalSumsMultipleProducts(String jacket, String top,
			String jacketPrice, String twoTopsPrice, String total) {
		CartPage cartPage = new CartPage(driver);
		cartPage.addProductToCart(jacket);
		cartPage.addProductToCart(top);
		cartPage.addProductToCart(top);
		cartPage.openCart();

		Assert.assertTrue(cartPage.getCartText().contains(jacketPrice));
		Assert.assertTrue(cartPage.getCartText().contains(twoTopsPrice));
		Assert.assertTrue(cartPage.getTotal().contains(total));
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

	@Test(priority = 6, dataProvider = "productData")
	public void negativeQuantityIsIgnored(String product) {
		CartPage cartPage = new CartPage(driver);
		cartPage.addProductToCart(product);
		cartPage.openCart();
		String quantityBefore = cartPage.getQuantity();

		cartPage.setQuantity("-1");
		cartPage.clickUpdate();

		Assert.assertEquals(cartPage.getQuantity(), quantityBefore);
		Reporter.log("pass", true);
	}

	@Test(priority = 7, dataProvider = "productData")
	public void veryLargeQuantityShowsCartError(String product) {
		CartPage cartPage = new CartPage(driver);
		cartPage.addProductToCart(product);
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