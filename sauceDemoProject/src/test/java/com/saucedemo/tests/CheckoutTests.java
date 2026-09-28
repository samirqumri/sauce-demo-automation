package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.saucedemo.pages.CheckoutPage;

public class CheckoutTests extends BaseTest {

	@DataProvider
	public Object[][] addressData() {
		return new Object[][] {
				{ "test.user@example.com", "Test Street", "Test City" }
		};
	}

	@DataProvider
	public Object[][] paymentData() {
		return new Object[][] {
				{ "test.user@example.com", "Test Street", "Test City", "1", "1230", "123" }
		};
	}

	@Test(priority = 1)
	public void checkoutWithAllFieldsEmptyShowsErrors() {
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		checkoutPage.goToCheckout("Grey jacket");
		checkoutPage.clickPayNow();

		Assert.assertTrue(checkoutPage.getEmailError().contains("Enter an email"));
		Assert.assertTrue(driver.getCurrentUrl().contains("checkout"));
		Reporter.log("pass", true);
	}

	@Test(priority = 2)
	public void checkoutWithInvalidEmailShowsError() {
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		checkoutPage.goToCheckout("Grey jacket");
		checkoutPage.enterEmail("not-an-email");
		checkoutPage.clickPayNow();

		Assert.assertTrue(checkoutPage.getEmailError().contains("valid email"));
		Assert.assertTrue(driver.getCurrentUrl().contains("checkout"));
		Reporter.log("pass", true);
	}

	@Test(priority = 3, dataProvider = "paymentData")
	public void firstNameEmptyIsAccepted(String email, String address, String city,
			String card, String expiry, String cvv) {
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		checkoutPage.goToCheckout("Grey jacket");
		checkoutPage.enterEmail(email);
		checkoutPage.enterLastName("Talalweh");
		checkoutPage.enterAddress(address, city);
		checkoutPage.enterCard(card, expiry, cvv, "Talalweh");
		checkoutPage.clickPayNow();

		Assert.assertTrue(checkoutPage.isOrderConfirmed());
		Reporter.log("pass", true);
	}

	@Test(priority = 4, dataProvider = "paymentData")
	public void firstNameWithSymbolsIsAccepted(String email, String address, String city,
			String card, String expiry, String cvv) {
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		checkoutPage.goToCheckout("Grey jacket");
		checkoutPage.enterEmail(email);
		checkoutPage.enterFirstName("@@@@@@");
		checkoutPage.enterLastName("Talalweh");
		checkoutPage.enterAddress(address, city);
		checkoutPage.enterCard(card, expiry, cvv, "@@@@@@ Talalweh");
		checkoutPage.clickPayNow();

		Assert.assertTrue(checkoutPage.isOrderConfirmed());
		Reporter.log("pass", true);
	}

	@Test(priority = 5, dataProvider = "addressData")
	public void lastNameEmptyShowsError(String email, String address, String city) {
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		checkoutPage.goToCheckout("Grey jacket");
		checkoutPage.enterEmail(email);
		checkoutPage.enterAddress(address, city);
		checkoutPage.clickPayNow();

		Assert.assertTrue(checkoutPage.isLastNameErrorShown());
		Reporter.log("pass", true);
	}

	@Test(priority = 6, dataProvider = "paymentData")
	public void lastNameWithSymbolsIsAccepted(String email, String address, String city,
			String card, String expiry, String cvv) {
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		checkoutPage.goToCheckout("Grey jacket");
		checkoutPage.enterEmail(email);
		checkoutPage.enterLastName("@@@@@@");
		checkoutPage.enterAddress(address, city);
		checkoutPage.enterCard(card, expiry, cvv, "@@@@@@");
		checkoutPage.clickPayNow();

		Assert.assertTrue(checkoutPage.isOrderConfirmed());
		Reporter.log("pass", true);
	}

	@Test(priority = 7, dataProvider = "paymentData")
	public void checkoutHappyPathWithValidTestData(String email, String address, String city,
			String card, String expiry, String cvv) {
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		checkoutPage.goToCheckout("Grey jacket");
		checkoutPage.enterEmail(email);
		checkoutPage.enterFirstName("Test");
		checkoutPage.enterLastName("User");
		checkoutPage.enterAddress(address, city);
		checkoutPage.enterCard(card, expiry, cvv, "Test User");
		checkoutPage.clickPayNow();

		Assert.assertTrue(checkoutPage.isOrderConfirmed());
		Reporter.log("pass", true);
	}
}