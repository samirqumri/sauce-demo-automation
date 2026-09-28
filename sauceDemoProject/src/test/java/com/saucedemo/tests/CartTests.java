package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.saucedemo.pages.CartPage;

public class CartTests extends BaseTest {

	@Test(priority = 1)
<<<<<<< HEAD
	public void cartShowsProductAndTotal() {

=======
	public void cartShowsProductAndTotal() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Catalog");
		

		ProductPage productPage = new ProductPage(driver);
		productPage.clickProductLink("Grey jacket");
	
		productPage.clickAddToCart();
	

		driver.get("https://sauce-demo.myshopify.com/cart");
		
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
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
<<<<<<< HEAD
	public void updatingQuantityRecalculatesTotal() {
=======
	public void updatingQuantityRecalculatesTotal() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Catalog");
		Thread.sleep(1000);

		ProductPage productPage = new ProductPage(driver);
		productPage.clickProductLink("Grey jacket");
		
		productPage.clickAddToCart();
	

		driver.get("https://sauce-demo.myshopify.com/cart");
		
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation

		CartPage cartPage = new CartPage(driver);
<<<<<<< HEAD

		cartPage.addProductToCart("Grey jacket");
		cartPage.openCart();

		cartPage.changeQuantity(3);

=======
		cartPage.changeQuantityByLine(1, 3);
		
		driver.navigate().refresh();
		
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
		Assert.assertEquals(cartPage.getQuantityValue(), "3");
		Assert.assertTrue(cartPage.getPageText().contains("165.00"));
		Reporter.log("pass", true);
	}

	@Test(priority = 3)
<<<<<<< HEAD
	public void cartTotalSumsMultipleProducts() {
=======
	public void cartTotalSumsMultipleProducts() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		ProductPage productPage = new ProductPage(driver);

		homePage.clickMenuLink("Catalog");
		
		productPage.clickProductLink("Grey jacket");
		
		productPage.clickAddToCart();
		

		homePage.clickMenuLink("Catalog");
		
		productPage.clickProductLink("Striped top");
	
		productPage.clickAddToCart();
		

		homePage.clickMenuLink("Catalog");
		
		productPage.clickProductLink("Striped top");
		
		productPage.clickAddToCart();
		

		driver.get("https://sauce-demo.myshopify.com/cart");
	
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation

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
<<<<<<< HEAD
	public void emptyCartShowsMessage() {
=======
	public void emptyCartShowsMessage() throws InterruptedException {
		driver.get("https://sauce-demo.myshopify.com/cart");
		
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation

		CartPage cartPage = new CartPage(driver);

		cartPage.openCart();

		String message = cartPage.getEmptyCartMessage();

		Assert.assertTrue(message.contains("It appears that your cart is currently empty"));
		Assert.assertEquals(cartPage.getCheckoutButtonsCount(), 0);
		Reporter.log("pass", true);
	}

	@Test(priority = 5)
<<<<<<< HEAD
	public void checkoutOpensWithCartProducts() {
=======
	public void checkoutOpensWithCartProducts() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Catalog");
		

		ProductPage productPage = new ProductPage(driver);
		productPage.clickProductLink("Noir jacket");
		
		productPage.clickAddToCart();
		
		driver.get("https://sauce-demo.myshopify.com/cart");
		
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation

		CartPage cartPage = new CartPage(driver);
<<<<<<< HEAD
=======
		cartPage.clickCheckout();
		
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation

		cartPage.addProductToCart("Noir jacket");
		cartPage.openCart();
		cartPage.clickCheckout();

		String summaryText = cartPage.getCheckoutSummaryText();

		Assert.assertTrue(summaryText.contains("Noir jacket"));
		Assert.assertTrue(summaryText.contains("Total"));
		Reporter.log("pass", true);
	}

	@Test(priority = 6)
<<<<<<< HEAD
	public void negativeQuantityIsIgnored() {

=======
	public void negativeQuantityIsIgnored() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Catalog");
		

		ProductPage productPage = new ProductPage(driver);
		productPage.clickProductLink("Grey jacket");
		
		productPage.clickAddToCart();
		

		driver.get("https://sauce-demo.myshopify.com/cart");
		
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
		CartPage cartPage = new CartPage(driver);

		cartPage.addProductToCart("Grey jacket");
		cartPage.openCart();

		String quantityBeforeUpdate = cartPage.getQuantityValue();

		cartPage.setQuantityValue("-1");
<<<<<<< HEAD
		cartPage.clickUpdateButtonAndWaitForReload();
=======
		cartPage.clickUpdateButton();
		
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation

		String quantityAfterUpdate = cartPage.getQuantityValue();

		Assert.assertEquals(quantityAfterUpdate, quantityBeforeUpdate);
		Reporter.log("pass", true);
	}

	@Test(priority = 7)
<<<<<<< HEAD
	public void veryLargeQuantityShowsCartError() {
=======
	public void veryLargeQuantityShowsCartError() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Catalog");
		

		ProductPage productPage = new ProductPage(driver);
		productPage.clickProductLink("Grey jacket");
		
		productPage.clickAddToCart();
		

		driver.get("https://sauce-demo.myshopify.com/cart");
		
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation

		CartPage cartPage = new CartPage(driver);

		cartPage.addProductToCart("Grey jacket");
		cartPage.openCart();

		cartPage.setQuantityValue("999999999");
<<<<<<< HEAD
		cartPage.clickUpdateButtonAndWaitForReload();

=======
		cartPage.clickUpdateButton();
		
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
		Assert.assertTrue(cartPage.isCartErrorPageShown());
		Reporter.log("pass", true);
	}

	@Test(priority = 8)
	public void miniCartShowsEmptyMessage() {

		CartPage cartPage = new CartPage(driver);

		cartPage.clickMiniCartToggle();
<<<<<<< HEAD
=======
	
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation

		Assert.assertTrue(cartPage.getMiniCartEmptyMessage().contains("Your cart is empty."));
		Reporter.log("pass", true);
	}
}