package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

<<<<<<< HEAD
	private String cartUrl = "https://sauce-demo.myshopify.com/cart";

	private By catalogLink = By.linkText("Catalog");
	private By addToCartButton = By.id("add");
	private By cartHasOneItem = By.xpath("//span[text()='(1)']");
	private By checkoutButton = By.id("checkout");
=======
	private By pageBody = By.tagName("body");
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation

	private By emailInput = By.id("email");
	private By firstNameInput = By.name("firstName");
	private By lastNameInput = By.name("lastName");
	private By addressInput = By.name("address1");
	private By cityInput = By.name("city");
	private By payNowButton = By.id("checkout-pay-button");

	private By cardNumberFrame = By.cssSelector("iframe[id^='card-fields-number']");
	private By expiryFrame = By.cssSelector("iframe[id^='card-fields-expiry']");
	private By cvvFrame = By.cssSelector("iframe[id^='card-fields-verification_value']");
	private By nameOnCardFrame = By.cssSelector("iframe[id^='card-fields-name']");

	private By emailError = By.id("error-for-email");
	private By lastNameError = By.xpath("//*[text()='Enter a last name']");
	private By orderConfirmed = By.xpath("//h2[text()='Thank you!']");

	public CheckoutPage(WebDriver driver) {
		super(driver);
	}

<<<<<<< HEAD
	public void goToCheckout(String productName) {
		WebElement catalog = wait.until(ExpectedConditions.elementToBeClickable(catalogLink));
		catalog.click();

		WebElement product = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//h3[text()='" + productName + "']")));
		product.click();

		WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
		addButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(cartHasOneItem));

		driver.get(cartUrl);
		WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
		checkout.click();
=======
	public String getOrderSummaryText() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(pageBody)).getText();
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
	}

<<<<<<< HEAD
	public void enterEmail(String email) {
		WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
		field.sendKeys(email);
=======
	public int getOrderItemsCount() {
		try {
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(orderSummaryItems)).size();
		} catch (org.openqa.selenium.TimeoutException e) {
			return 0;
		}
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
	}

<<<<<<< HEAD
	public void enterFirstName(String firstName) {
		WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
		field.sendKeys(firstName);
	}

	public void enterLastName(String lastName) {
		WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput));
		field.sendKeys(lastName);
	}

	public void enterAddress(String address, String city) {
		WebElement addressField = wait.until(ExpectedConditions.visibilityOfElementLocated(addressInput));
		addressField.sendKeys(address);

		WebElement cityField = wait.until(ExpectedConditions.visibilityOfElementLocated(cityInput));
		cityField.sendKeys(city);
	}

	public void enterCard(String number, String expiry, String cvv, String nameOnCard) {
		typeInFrame(cardNumberFrame, By.id("number"), number);
		typeInFrame(expiryFrame, By.id("expiry"), expiry);
		typeInFrame(cvvFrame, By.id("verification_value"), cvv);
		typeInFrame(nameOnCardFrame, By.id("name"), nameOnCard);
	}

	private void typeInFrame(By frame, By field, String value) {
		WebElement frameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(frame));
		driver.switchTo().frame(frameElement);

		WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(field));
		input.clear();
		for (char c : value.toCharArray()) {
			input.sendKeys(String.valueOf(c));
		}

		driver.switchTo().defaultContent();
=======
	public void ToRemoveOrder() {
		wait.until(ExpectedConditions.elementToBeClickable(removeOrder)).click();
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
	}

	public void clickPayNow() {
<<<<<<< HEAD
		WebElement payNow = wait.until(ExpectedConditions.elementToBeClickable(payNowButton));
		payNow.click();
=======
		wait.until(ExpectedConditions.elementToBeClickable(payNowButton)).click();
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
	}

<<<<<<< HEAD
	public String getEmailError() {
		WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(emailError));
		return error.getText();
=======
	public String getEmailErrorText() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(emailErrorMessage)).getText();
	}

	public void enterFirstName(String value) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)).sendKeys(value);
	}

	public void enterLastName(String value) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput)).sendKeys(value);
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
	}

	public boolean isLastNameErrorShown() {
<<<<<<< HEAD
		WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameError));
		return error.isDisplayed();
=======
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
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
	}

	public boolean isOrderConfirmed() {
<<<<<<< HEAD
		WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(orderConfirmed));
		return message.isDisplayed();
=======
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(orderConfirmedText));
		return true;
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
	}
<<<<<<< HEAD
}
=======

}
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
