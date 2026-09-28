
package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

	private By pageBody = By.tagName("body");

	private By orderSummaryItems = By.cssSelector(".order-summary__item, .product");

	private By removeOrder = By.xpath("//a[text()='x']");

	private By payNowButton = By.id("checkout-pay-button");

	private By emailErrorMessage = By.id("error-for-email");

	private By firstNameInput = By.name("firstName");

	private By lastNameInput = By.name("lastName");

	private By addressInput = By.name("address1");

	private By cityInput = By.name("city");

	private By numberFrame = By.cssSelector("iframe[id^='card-fields-number-']");

	private By expiryFrame = By.cssSelector("iframe[id^='card-fields-expiry-']");

	private By securityCodeFrame = By.cssSelector("iframe[id^='card-fields-verification_value-']");

	private By nameOnCardFrame = By.cssSelector("iframe[id^='card-fields-name-']");

	private By orderConfirmedText = By.xpath("//*[contains(text(),'Thank you')]");

	public CheckoutPage(WebDriver driver) {
		super(driver);
	}

	public String getOrderSummaryText() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(pageBody)).getText();
	}

	public int getOrderItemsCount() {
		try {
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(orderSummaryItems)).size();
		} catch (org.openqa.selenium.TimeoutException e) {
			return 0;
		}
	}

	public void ToRemoveOrder() {
		wait.until(ExpectedConditions.elementToBeClickable(removeOrder)).click();
	}

	public void clickPayNow() {
		wait.until(ExpectedConditions.elementToBeClickable(payNowButton)).click();
	}

	public String getEmailErrorText() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(emailErrorMessage)).getText();
	}

	public void enterFirstName(String value) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)).sendKeys(value);
	}

	public void enterLastName(String value) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput)).sendKeys(value);
	}

	public boolean isLastNameErrorShown() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(pageBody)).getText()
				.contains("Enter a last name");
	}

	public void enterAddress(String value) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(addressInput)).sendKeys(value);
	}

	public void enterCity(String value) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(cityInput)).sendKeys(value);
	}

	public void enterCardNumber(String value) {
		WebElement frameElement = wait.until(ExpectedConditions.presenceOfElementLocated(numberFrame));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("number"))).sendKeys(value);
		driver.switchTo().defaultContent();
	}

	public void enterExpiryDate(String value) {
		WebElement frameElement = wait.until(ExpectedConditions.presenceOfElementLocated(expiryFrame));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
		WebElement expiryField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("expiry")));
		expiryField.sendKeys(value.substring(0, 2));
		expiryField.sendKeys(value.substring(2));
		driver.switchTo().defaultContent();
	}

	public void enterSecurityCode(String value) {
		WebElement frameElement = wait.until(ExpectedConditions.presenceOfElementLocated(securityCodeFrame));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("verification_value"))).sendKeys(value);
		driver.switchTo().defaultContent();
	}

	public void enterNameOnCard(String value) {
		WebElement frameElement = wait.until(ExpectedConditions.presenceOfElementLocated(nameOnCardFrame));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
		WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
		nameField.clear();
		nameField.sendKeys(value);
		driver.switchTo().defaultContent();
	}

	public boolean isOrderConfirmed() {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(orderConfirmedText));
		return true;
	}

}
