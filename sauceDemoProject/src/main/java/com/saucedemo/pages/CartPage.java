package com.saucedemo.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
	private WebDriver driver;

	private By badgeCount = By.id("cart-target-desktop");
	private By checkoutButton = By.id("checkout");
	private By emptyMessage = By.cssSelector("#cart p");
	private By removeLink = By.linkText("Remove");
	private By quantityInput = By.cssSelector("input[name='updates[]']");
	private By updateButton = By.id("update");
	private By pageBody = By.tagName("body");

	public CartPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getCartBadgeText() {
		return driver.findElement(badgeCount).getText().trim();
	}

	public int getCartCount() {
		try {
			String text = driver.findElement(badgeCount).getText();
			text = text.replace("(", "").replace(")", "").trim();

			if (text.isEmpty()) {
				return 0;
			}

			return Integer.parseInt(text);

		} catch (org.openqa.selenium.NoSuchElementException e) {
			return 0;
		}
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
		List<WebElement> matches = driver.findElements(quantityInput);
		for (WebElement el : matches) {
			if (el.isDisplayed()) {
				return el.getAttribute("value");
			}
		}
		return null;
	}

	public void setQuantityValue(String value) {
		List<WebElement> matches = driver.findElements(quantityInput);
		for (WebElement el : matches) {
			if (el.isDisplayed()) {
				el.clear();
				el.sendKeys(value);
				return;
			}
		}
	}

	public void clickUpdateButton() {
		driver.findElement(updateButton).click();
	}

	public boolean isCartErrorPageShown() {
		return driver.findElement(pageBody).getText().contains("Something went wrong");
	}

	public String getPageText() {
		return driver.findElement(pageBody).getText();
	}
}