package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

	private String cartUrl = "https://sauce-demo.myshopify.com/cart";

	// Header
	private By catalogLink = By.linkText("Catalog");
	private By cartCount = By.id("cart-target-desktop");
	private By miniCartToggle = By.className("toggle-drawer");
	private By miniCartEmptyMessage = By.cssSelector("#drawer .empty");

	// Product page
	private By addToCartButton = By.id("add");

	// Cart page
	private By cartSection = By.id("cart");
	private By quantityInput = By.cssSelector("#cart .quantity input");
	private By cartTotal = By.cssSelector("#cart h2");
	private By updateButton = By.id("update");
	private By checkoutButton = By.id("checkout");
	private By emptyCartMessage = By.cssSelector("#cart p");
	private By errorMessage = By.xpath("//*[contains(text(),'Something went wrong')]");

	// Checkout page
	private By payNowButton = By.id("checkout-pay-button");
	private By pageBody = By.tagName("body");

	private By dynamicProduct(String productName) {
		return By.xpath("//*[contains(text(),'" + productName + "')]");
	}

	private By dynamicCartCount(int expectedCount) {
		return By.xpath("//*[contains(text(),'My Cart (" + expectedCount + ")')]");
	}

	public CartPage(WebDriver driver) {
		super(driver);
	}

	public void addProductToCart(String productName) {
		int expectedCount = getCartCount() + 1;
		try {
			WebElement catalog = wait.until(ExpectedConditions.elementToBeClickable(catalogLink));
			catalog.click();
		} catch (org.openqa.selenium.TimeoutException e) {
			return;
		}
		addProductToCart(productName, expectedCount);
	}

	public void addProductToCart(String productName, int expectedCount) {
		WebElement product = wait.until(ExpectedConditions.elementToBeClickable(dynamicProduct(productName)));
		product.click();

		WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
		addButton.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(dynamicCartCount(expectedCount)));
	}

	public int getCartCount() {
		WebElement count = wait.until(ExpectedConditions.visibilityOfElementLocated(cartCount));
		return Integer.parseInt(count.getText().replaceAll("[^0-9]", ""));
	}

	public void openCart() {
		driver.get(cartUrl);
		wait.until(ExpectedConditions.visibilityOfElementLocated(cartSection));
	}

	public String getCartText() {
		WebElement cart = wait.until(ExpectedConditions.visibilityOfElementLocated(cartSection));
		return cart.getText();
	}

	public String getTotal() {
		WebElement total = wait.until(ExpectedConditions.visibilityOfElementLocated(cartTotal));
		return total.getText();
	}

	public String getQuantity() {
		WebElement quantity = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInput));
		return quantity.getAttribute("value");
	}

	public void setQuantity(String value) {
		WebElement quantity = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInput));
		quantity.clear();
		quantity.sendKeys(value);
	}

	public void clickUpdate() {
		WebElement update = wait.until(ExpectedConditions.elementToBeClickable(updateButton));
		update.click();
	}

	public void clickCheckout() {
		WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
		checkout.click();
	}

	public String getEmptyCartMessage() {
		WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartMessage));
		return message.getText();
	}

	public boolean isCheckoutButtonShown() {
		return driver.findElements(checkoutButton).size() > 0;
	}

	public boolean isErrorShown() {
		WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
		return error.isDisplayed();
	}

	public void openMiniCart() {
		WebElement toggle = wait.until(ExpectedConditions.elementToBeClickable(miniCartToggle));
		toggle.click();
	}

	public String getMiniCartEmptyMessage() {
		WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(miniCartEmptyMessage));
		return message.getText();
	}

	public String getCheckoutPageText() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(payNowButton));
		return driver.findElement(pageBody).getText();
	}
}
