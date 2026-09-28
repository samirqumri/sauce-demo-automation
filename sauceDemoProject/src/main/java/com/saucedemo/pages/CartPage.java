package com.saucedemo.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

	private By badgeCount = By.id("cart-target-desktop");
	private By checkoutButton = By.id("checkout");
	private By emptyMessage = By.cssSelector("#cart p");
	private By removeLink = By.linkText("Remove");
	private By quantityInput = By.xpath("//input[@name='updates[]' and not(ancestor::*[@id='drawer'])]");
	private By updateButton = By.id("update");
	private By pageBody = By.tagName("body");
	private By miniCartToggle = By.cssSelector("a.toggle-drawer.cart.desktop");
	private By miniCartEmptyMessage = By.cssSelector("#drawer p.empty");
	private By cartContainer = By.id("cart");
	private By errorMessage = By.xpath("//*[contains(text(),'Something went wrong')]");

	// من ProductPage
	private By addToCartButton = By.xpath(
			"//*[self::button or self::input][contains(text(),'Add to Cart') or contains(@value,'Add to Cart')]");

	// من CheckoutPage
	private By payNowButton = By.id("checkout-pay-button");

	private String cartUrl = "https://sauce-demo.myshopify.com/cart";

	public CartPage(WebDriver driver) {
		super(driver);
	}

	public void openCart() {
		driver.get(cartUrl);
		wait.until(ExpectedConditions.visibilityOfElementLocated(cartContainer));
	}

	// من HomePage
	public void clickMenuLink(String linkText) {
		WebElement menuLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(linkText)));
		menuLink.click();
	}

	// من ProductPage
	public void clickProductLink(String productName) {
		String productSlug = productName.toLowerCase().replace(" ", "-");
		By productLink = By.xpath("//a[contains(@href,'" + productSlug + "') and not(ancestor::*[@id='drawer'])]");

		wait.until(ExpectedConditions.visibilityOfElementLocated(productLink));
		List<WebElement> matches = driver.findElements(productLink);

		for (WebElement el : matches) {
			if (el.isDisplayed()) {
				el.click();
				return;
			}
		}
	}

	// من ProductPage
	public void clickAddToCart() {
		int expectedCount = getCartCount() + 1;
		WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
		addButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='cart-target-desktop' and contains(normalize-space(.), '(" + expectedCount + ")')]")));
	}

	// من CheckoutPage
	public String getOrderSummaryText() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(payNowButton));
		return driver.findElement(pageBody).getText();
	}

	public String getCartBadgeText() {
		WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(badgeCount));
		return badge.getText().trim();
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
		WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
		checkout.click();
	}

	public String getEmptyCartMessage() {
		WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(emptyMessage));
		return message.getText();
	}

	public int getCheckoutButtonsCount() {
		return driver.findElements(checkoutButton).size();
	}

	public void clickRemoveItem() {
		WebElement remove = wait.until(ExpectedConditions.elementToBeClickable(removeLink));
		remove.click();
	}

	public void changeQuantityToZero() {
		changeQuantityByLine(1, 0);
	}

	public void changeQuantityByLine(int lineNumber, int quantity) {
		String script = "var done = arguments[arguments.length - 1];"
				+ "fetch('/cart/change.js', {method:'POST', headers:{'Content-Type':'application/json'}, body: JSON.stringify({line: arguments[0], quantity: arguments[1]})})"
				+ ".then(function(){ done(); }, function(){ done(); });";
		((JavascriptExecutor) driver).executeAsyncScript(script, lineNumber, quantity);
	}

	public String getQuantityValue() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInput));
		List<WebElement> matches = driver.findElements(quantityInput);
		for (WebElement el : matches) {
			if (el.isDisplayed()) {
				return el.getAttribute("value");
			}
		}
		return null;
	}

	public void setQuantityValue(String value) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInput));
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
		WebElement update = wait.until(ExpectedConditions.elementToBeClickable(updateButton));
		update.click();
	}

	public boolean isCartErrorPageShown() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	public String getPageText() {
		WebElement body = wait.until(ExpectedConditions.visibilityOfElementLocated(pageBody));
		return body.getText();
	}

	public void clickMiniCartToggle() {
		WebElement toggle = wait.until(ExpectedConditions.elementToBeClickable(miniCartToggle));
		toggle.click();
	}

	public String getMiniCartEmptyMessage() {
		WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(miniCartEmptyMessage));
		return message.getText();
	}
}