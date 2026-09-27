package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;

public class CartPage {
	private WebDriver driver;

	private By badgeCount = By.id("id");
	private By checkoutButton = By.name("");
	private By emptyMessage = By.className("");
	private By removeLink = By.linkText("Remove");
	private By quantityInput = By.cssSelector(".quantity.desktop input[name='updates[]']");
	private By pageBody = By.tagName("body");

	public CartPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getCartBadgeText() {
		return driver.findElement(badgeCount).getText().trim();
	}

	public void clickCheckout() {
		driver.findElement(checkoutButton).click();
	}

	public String getEmptyCartMessage() {
		return driver.findElement(emptyMessage).getText();
	}

	public int getCheckoutButtonsCount() {
		return driver.findElements(checkoutButton).size();
	}

	public void clickRemoveItem() {
		driver.findElement(removeLink).click();
	}

	public void changeQuantityToZero() {
		changeQuantityByLine(1, 0);
	}

	
	public void changeQuantityByLine(int lineNumber, int quantity) {
		String script = "fetch('/cart/change.js', {method:'POST', headers:{'Content-Type':'application/json'}, body: JSON.stringify({line: arguments[0], quantity: arguments[1]})});";
		((JavascriptExecutor) driver).executeScript(script, lineNumber, quantity);
	}

	public String getQuantityValue() {
		return driver.findElement(quantityInput).getAttribute("value");
	}

	public String getPageText() {
		return driver.findElement(pageBody).getText();
	}
}