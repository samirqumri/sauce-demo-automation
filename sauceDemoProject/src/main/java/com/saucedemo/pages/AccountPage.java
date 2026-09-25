package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {
	private WebDriver driver;

	private By accountDetails = By.cssSelector(".account__details, .customer-details");
	private By orderHistory = By.cssSelector(".order-history, table.order-list");
	private By addAddressButton = By.linkText("Add a new address");
	private By saveAddressButton = By.cssSelector("button[type='submit']");

	public AccountPage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isAccountDetailsDisplayed() {
		return driver.findElement(accountDetails).isDisplayed();
	}

	public boolean hasOrderHistory() {
		return driver.findElements(orderHistory).size() > 0;
	}

	public void clickAddAddress() {
		driver.findElement(addAddressButton).click();
	}

	public void clickSaveAddress() {
		driver.findElement(saveAddressButton).click();
	}
}