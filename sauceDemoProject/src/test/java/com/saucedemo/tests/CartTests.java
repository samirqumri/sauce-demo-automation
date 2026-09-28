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
<<<<<<< HEAD

	public void cartShowsProductAndTotal() throws InterruptedException {

		HomePage homePage = new HomePage(driver);

		homePage.clickMenuLink("Catalog");

		ProductPage productPage = new ProductPage(driver);

		productPage.clickProductLink("Grey jacket");

		productPage.clickAddToCart();
		Thread.sleep(2000);
		driver.get("https://sauce-demo.myshopify.com/cart");
=======
	public void cartShowsProductAndTotal() {
		CartPage cartPage = new CartPage(driver);
<<<<<<< HEAD
		cartPage.addProductToCart("Grey jacket");
=======
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git

<<<<<<< HEAD
		CartPage cartPage = new CartPage(driver);
=======
		cartPage.clickMenuLink("Catalog");
		cartPage.clickProductLink("Grey jacket");
		cartPage.clickAddToCart();
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git

>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
		cartPage.openCart();

<<<<<<< HEAD
		Assert.assertTrue(cartPage.getCartText().contains("Grey jacket"));
		Assert.assertTrue(cartPage.getTotal().contains("55.00"));
		Assert.assertEquals(cartPage.getQuantity(), "1");
=======
		Assert.assertTrue(cartText.contains("Grey jacket"));

		Assert.assertTrue(cartText.contains("55.00"));

		Assert.assertEquals(cartPage.getQuantityValue(), "1");

>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
		Reporter.log("pass", true);

	}

<<<<<<< HEAD
	@Test
	public void updatingQuantityRecalculatesTotal() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Catalog");

		ProductPage productPage = new ProductPage(driver);
		productPage.clickProductLink("Grey jacket");
		productPage.clickAddToCart();
		Thread.sleep(2000);
=======
	@Test(priority = 2)
	public void updatingQuantityRecalculatesTotal() {
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git
		CartPage cartPage = new CartPage(driver);
<<<<<<< HEAD
		cartPage.addProductToCart("Grey jacket");
=======
<<<<<<< HEAD
=======

		cartPage.clickMenuLink("Catalog");
		cartPage.clickProductLink("Grey jacket");
		cartPage.clickAddToCart();

>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
		cartPage.openCart();
<<<<<<< HEAD
=======
<<<<<<< HEAD

		// Fixed parameter to match your page method
		cartPage.changeQuantity(3);
=======
		cartPage.changeQuantityByLine(1, 3);
		driver.navigate().refresh();
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation

		cartPage.setQuantity("3");
		cartPage.clickUpdate();

		Assert.assertEquals(cartPage.getQuantity(), "3");
		Assert.assertTrue(cartPage.getTotal().contains("165.00"));
		Reporter.log("pass", true);
	}

	@Test(priority = 3)
<<<<<<< HEAD

	public void cartTotalSumsMultipleProducts() throws InterruptedException {

		HomePage homePage = new HomePage(driver);

		ProductPage productPage = new ProductPage(driver);

		homePage.clickMenuLink("Catalog");

		productPage.clickProductLink("Grey jacket");

		productPage.clickAddToCart();
		Thread.sleep(2000);
		homePage.clickMenuLink("Catalog");

		productPage.clickProductLink("Striped top");

		productPage.clickAddToCart();
		Thread.sleep(2000);
		homePage.clickMenuLink("Catalog");

		productPage.clickProductLink("Striped top");

		productPage.clickAddToCart();
		Thread.sleep(2000);
		driver.get("https://sauce-demo.myshopify.com/cart");

=======
	public void cartTotalSumsMultipleProducts() {
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git
		CartPage cartPage = new CartPage(driver);
<<<<<<< HEAD
		cartPage.addProductToCart("Grey jacket");
		cartPage.addProductToCart("Striped top");
		cartPage.addProductToCart("Striped top");
=======

<<<<<<< HEAD
=======
		cartPage.clickMenuLink("Catalog");
		cartPage.clickProductLink("Grey jacket");
		cartPage.clickAddToCart();

		cartPage.clickMenuLink("Catalog");
		cartPage.clickProductLink("Striped top");
		cartPage.clickAddToCart();

		cartPage.clickMenuLink("Catalog");
		cartPage.clickProductLink("Striped top");
		cartPage.clickAddToCart();

>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
		cartPage.openCart();
<<<<<<< HEAD
=======
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git
		String cartText = cartPage.getPageText();
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation

<<<<<<< HEAD
		Assert.assertTrue(cartPage.getCartText().contains("55.00"));
		Assert.assertTrue(cartPage.getCartText().contains("100.00"));
		Assert.assertTrue(cartPage.getTotal().contains("155.00"));
=======
		Assert.assertTrue(cartText.contains("55.00"));

		Assert.assertTrue(cartText.contains("100.00"));

		Assert.assertTrue(cartText.contains("155.00"));

>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
		Reporter.log("pass", true);

	}

	@Test(priority = 4)
<<<<<<< HEAD

	public void emptyCartShowsMessage() throws InterruptedException {

		driver.get("https://sauce-demo.myshopify.com/cart");

=======
	public void emptyCartShowsMessage() {
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git
		CartPage cartPage = new CartPage(driver);
<<<<<<< HEAD
=======
		cartPage.openCart();
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git

<<<<<<< HEAD
		Assert.assertTrue(cartPage.getEmptyCartMessage().contains("It appears that your cart is currently empty"));
		Assert.assertFalse(cartPage.isCheckoutButtonShown());
=======
		String message = cartPage.getEmptyCartMessage();

		Assert.assertTrue(message.contains("It appears that your cart is currently empty"));

		Assert.assertEquals(cartPage.getCheckoutButtonsCount(), 0);

>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
		Reporter.log("pass", true);

	}

	@Test(priority = 5)
<<<<<<< HEAD

	public void checkoutOpensWithCartProducts() throws InterruptedException {

		HomePage homePage = new HomePage(driver);

		homePage.clickMenuLink("Catalog");

		ProductPage productPage = new ProductPage(driver);

		productPage.clickProductLink("Noir jacket");

		productPage.clickAddToCart();
		Thread.sleep(2000);
		driver.get("https://sauce-demo.myshopify.com/cart");

=======
	public void checkoutOpensWithCartProducts() {
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git
		CartPage cartPage = new CartPage(driver);
<<<<<<< HEAD
		cartPage.addProductToCart("Noir jacket");
=======

<<<<<<< HEAD
=======
		cartPage.clickMenuLink("Catalog");
		cartPage.clickProductLink("Noir jacket");
		cartPage.clickAddToCart();

>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
		cartPage.openCart();
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git
		cartPage.clickCheckout();

<<<<<<< HEAD
		String checkoutText = cartPage.getCheckoutPageText();
		Assert.assertTrue(checkoutText.contains("Noir jacket"));
		Assert.assertTrue(checkoutText.contains("Total"));
=======
<<<<<<< HEAD
		CheckoutPage checkoutPage = new CheckoutPage(driver);

		String summaryText = checkoutPage.getOrderSummaryText();
=======
		String summaryText = cartPage.getOrderSummaryText();
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git

		Assert.assertTrue(summaryText.contains("Noir jacket"));

		Assert.assertTrue(summaryText.contains("Total"));

>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
		Reporter.log("pass", true);

	}

	@Test(priority = 6)
<<<<<<< HEAD

	public void negativeQuantityIsIgnored() throws InterruptedException {

		HomePage homePage = new HomePage(driver);

		homePage.clickMenuLink("Catalog");

		ProductPage productPage = new ProductPage(driver);

		productPage.clickProductLink("Grey jacket");

		productPage.clickAddToCart();
		Thread.sleep(2000);
		driver.get("https://sauce-demo.myshopify.com/cart");
=======
	public void negativeQuantityIsIgnored() {
		CartPage cartPage = new CartPage(driver);
<<<<<<< HEAD
		cartPage.addProductToCart("Grey jacket");
=======
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git

<<<<<<< HEAD
		CartPage cartPage = new CartPage(driver);
=======
		cartPage.clickMenuLink("Catalog");
		cartPage.clickProductLink("Grey jacket");
		cartPage.clickAddToCart();
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git

>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
		cartPage.openCart();
		String quantityBefore = cartPage.getQuantity();

<<<<<<< HEAD
		cartPage.setQuantity("-1");
		cartPage.clickUpdate();
=======
		cartPage.setQuantityValue("-1");
<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git
		cartPage.clickUpdateButton();
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation

<<<<<<< HEAD
		Assert.assertEquals(cartPage.getQuantity(), quantityBefore);
=======
		String quantityAfterUpdate = cartPage.getQuantityValue();
		Assert.assertEquals(quantityAfterUpdate, quantityBeforeUpdate);

>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
		Reporter.log("pass", true);

	}

	@Test(priority = 7)
<<<<<<< HEAD

	public void veryLargeQuantityShowsCartError() throws InterruptedException {

		HomePage homePage = new HomePage(driver);

		homePage.clickMenuLink("Catalog");

		ProductPage productPage = new ProductPage(driver);

		productPage.clickProductLink("Grey jacket");

		productPage.clickAddToCart();
		Thread.sleep(2000);
		driver.get("https://sauce-demo.myshopify.com/cart");

=======
	public void veryLargeQuantityShowsCartError() {
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git
		CartPage cartPage = new CartPage(driver);
<<<<<<< HEAD
		cartPage.addProductToCart("Grey jacket");
=======

<<<<<<< HEAD
=======
		cartPage.clickMenuLink("Catalog");
		cartPage.clickProductLink("Grey jacket");
		cartPage.clickAddToCart();

>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
		cartPage.openCart();
<<<<<<< HEAD
=======
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git
		cartPage.setQuantityValue("999999999");
<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git
		cartPage.clickUpdateButton();
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation

<<<<<<< HEAD
		cartPage.setQuantity("999999999");
		cartPage.clickUpdate();

		Assert.assertTrue(cartPage.isErrorShown());
=======
		Assert.assertTrue(cartPage.isCartErrorPageShown());

>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
		Reporter.log("pass", true);

	}

	@Test(priority = 8)
<<<<<<< HEAD

	public void miniCartShowsEmptyMessage() throws InterruptedException {

=======
	public void miniCartShowsEmptyMessage() {
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git
		CartPage cartPage = new CartPage(driver);
		cartPage.openMiniCart();

		Assert.assertTrue(cartPage.getMiniCartEmptyMessage().contains("Your cart is empty."));
<<<<<<< HEAD
		Reporter.log("pass", true);
=======
<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
	}

}
