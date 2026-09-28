package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountPage extends BasePage {

	private By accountDetails = By.cssSelector(".account__details, .customer-details");
	private By orderHistory = By.cssSelector(".order-history, table.order-list");
	private By addAddressButton = By.linkText("Add a new address");
	private By saveAddressButton = By.cssSelector("button[type='submit']");

	public AccountPage(WebDriver driver) {
		super(driver);
	}

	public boolean isAccountDetailsDisplayed() {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(accountDetails));
		return element.isDisplayed();
	}

	public boolean hasOrderHistory() {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(orderHistory));
		return true;
	}

	public void clickAddAddress() {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(addAddressButton));
		element.click();
	}

	public void clickSaveAddress() {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(saveAddressButton));
		element.click();
	}
}