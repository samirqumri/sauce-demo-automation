package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

	private String cartUrl = "https://sauce-demo.myshopify.com/cart";

	private By catalogLink = By.linkText("Catalog");
	private By addToCartButton = By.id("add");
	private By cartHasOneItem = By.xpath("//span[text()='(1)']");
	private By checkoutButton = By.id("checkout");

	private By removeOrder = By.xpath("//a[text()='x']");
	private By orderSummaryItems = By.cssSelector(".order-summary__item, .product");
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
	private By orderConfirmed = By.xpath("//h2[contains(text(),'Thank you')]");

	public CheckoutPage(WebDriver driver) {
		super(driver);
	}

	public void goToCheckout(String productName) {
		WebElement catalog = wait.until(ExpectedConditions.elementToBeClickable(catalogLink));
		catalog.click();

		WebElement product = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[text()='" + productName + "']")));
		product.click();

		WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
		addButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(cartHasOneItem));

		driver.get(cartUrl);
		WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
		checkout.click();
	}

	public void enterEmail(String email) {
		WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
		field.sendKeys(email);
	}

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

		WebElement input = wait.until(ExpectedConditions.elementToBeClickable(field));
		input.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
		for (char c : value.toCharArray()) {
			input.sendKeys(String.valueOf(c));
		}

		driver.switchTo().defaultContent();
	}

	public void clickPayNow() {
		WebElement payNow = wait.until(ExpectedConditions.elementToBeClickable(payNowButton));
		payNow.click();
	}

	public void ToRemoveOrder() {
		WebElement remove = wait.until(ExpectedConditions.elementToBeClickable(removeOrder));
		remove.click();
	}

	public String getEmailError() {
		WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(emailError));
		return error.getText();
	}

	public boolean isLastNameErrorShown() {
		WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameError));
		return error.isDisplayed();
	}

	public boolean isOrderConfirmed() {
		WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(orderConfirmed));
		return message.isDisplayed();
	}

	public void ToRemoveOrder1() {
		WebElement remove = wait.until(ExpectedConditions.elementToBeClickable(removeOrder));
		remove.click();
	}

	public int getOrderItemsCount() {
		try {
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(orderSummaryItems)).size();
		} catch (org.openqa.selenium.TimeoutException e) {
			return 0;
		}
	}

}