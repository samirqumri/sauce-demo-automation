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

	public CartPage(WebDriver driver) {
		super(driver);
	}

<<<<<<< HEAD
	public void addProductToCart(String productName) {
=======
<<<<<<< HEAD
	private WebElement waitForVisible(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
=======
	public void openCart() {
		driver.get(cartUrl);
		wait.until(ExpectedConditions.visibilityOfElementLocated(cartContainer));
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git
	}

<<<<<<< HEAD
	private WebElement waitForClickable(By locator) {
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	private WebElement waitForFirstDisplayed(By locator) {
		return wait.until((ExpectedCondition<WebElement>) d -> {
			for (WebElement el : d.findElements(locator)) {
				if (el.isDisplayed()) {
					return el;
				}
			}
			return null;
		});
	}

=======
	// من HomePage
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git
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
<<<<<<< HEAD
		waitForClickable(addToCartButton).click();
	}

	public void addProductToCart(String productName) {
=======
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
		int expectedCount = getCartCount() + 1;

		WebElement catalog = wait.until(ExpectedConditions.elementToBeClickable(catalogLink));
		catalog.click();

		WebElement product = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//h3[text()='" + productName + "']")));
		product.click();

		WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
		addButton.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[text()='(" + expectedCount + ")']")));
	}

	public int getCartCount() {
<<<<<<< HEAD
		WebElement count = wait.until(ExpectedConditions.visibilityOfElementLocated(cartCount));
		return Integer.parseInt(count.getText().replaceAll("[^0-9]", ""));
	}
=======
		try {
			String text = wait.until(ExpectedConditions.visibilityOfElementLocated(badgeCount)).getText();
			text = text.replace("(", "").replace(")", "").trim();
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation

	public void openCart() {
		driver.get(cartUrl);
		wait.until(ExpectedConditions.visibilityOfElementLocated(cartSection));
	}

	public String getCartText() {
		WebElement cart = wait.until(ExpectedConditions.visibilityOfElementLocated(cartSection));
		return cart.getText();
	}

<<<<<<< HEAD
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
=======
		} catch (org.openqa.selenium.TimeoutException e) {
			return 0;
		}
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
	}

	public void clickCheckout() {
		WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
		checkout.click();
	}

<<<<<<< HEAD
	public String getCheckoutSummaryText() {
		wait.until(ExpectedConditions.urlContains("checkout"));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(pageBody, "Total"));
		return driver.findElement(pageBody).getText();
=======
	public String getEmptyCartMessage() {
		WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartMessage));
		return message.getText();
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git
	}

	public boolean isCheckoutButtonShown() {
		return driver.findElements(checkoutButton).size() > 0;
	}

<<<<<<< HEAD
	public boolean isErrorShown() {
		WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
		return error.isDisplayed();
=======
<<<<<<< HEAD
	public String getEmptyCartMessage() {
		return waitForVisible(emptyMessage).getText();
	}

=======
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git
	public void clickRemoveItem() {
<<<<<<< HEAD
		waitForClickable(removeLink).click();
	}

	public void changeQuantity(int quantity) {
		setQuantityValue(String.valueOf(quantity));
		clickUpdateButtonAndWaitForReload();
=======
		WebElement remove = wait.until(ExpectedConditions.elementToBeClickable(removeLink));
		remove.click();
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
	}

<<<<<<< HEAD
	public void openMiniCart() {
=======
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
<<<<<<< HEAD
		waitForClickable(updateButton).click();
	}

	public void clickUpdateButtonAndWaitForReload() {
		WebElement button = waitForClickable(updateButton);
		button.click();
		wait.until(ExpectedConditions.stalenessOf(button));
		waitForVisible(pageBody);
=======
		WebElement update = wait.until(ExpectedConditions.elementToBeClickable(updateButton));
		update.click();
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git
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
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
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