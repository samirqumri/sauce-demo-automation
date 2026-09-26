package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
	private WebDriver driver;

	private By emailField = By.id("checkout_email");
	private By firstNameField = By.id("checkout_shipping_address_first_name");
	private By lastNameField = By.id("checkout_shipping_address_last_name");
	private By addressField = By.id("checkout_shipping_address_address1");
	private By continueButton = By.cssSelector("button[type='submit']");
	private By errorMessages = By.cssSelector(".field__message--error, .notice--error");
	private By orderSummaryItems = By.cssSelector(".order-summary__item, .product");
	private By removeOrder = By.xpath("//a[text()='x']");

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterEmail(String email) {
		driver.findElement(emailField).sendKeys(email);
	}

	public void fillShippingDetails(String firstName, String lastName, String address) {
		driver.findElement(firstNameField).sendKeys(firstName);
		driver.findElement(lastNameField).sendKeys(lastName);
		driver.findElement(addressField).sendKeys(address);
	}

	public void clickContinue() {
		driver.findElement(continueButton).click();
	}

	public boolean hasValidationErrors() {
		return driver.findElements(errorMessages).size() > 0;
	}

	public int getOrderItemsCount() {
		return driver.findElements(orderSummaryItems).size();
	}

	public void ToRemoveOrder() {
		driver.findElement(removeOrder).click();
	}
}