package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

	private static final String CART_URL = "https://sauce-demo.myshopify.com/cart";

	private By badgeCount = By.id("cart-target-desktop");
	private By checkoutButton = By.id("checkout");
	private By emptyMessage = By.cssSelector("#cart p");
	private By removeLink = By.linkText("Remove");
	private By quantityInput = By.cssSelector("input[name='updates[]']");
	private By updateButton = By.id("update");
	private By pageBody = By.tagName("body");
	private By miniCartToggle = By.cssSelector("a.toggle-drawer.cart.desktop");
	private By miniCartEmptyMessage = By.cssSelector("#drawer p.empty");
	private By addToCartButton = By.xpath(
			"//*[self::button or self::input][contains(text(),'Add to Cart') or contains(@value,'Add to Cart')]");

	public CartPage(WebDriver driver) {
		super(driver);
	}

	

	private WebElement waitForVisible(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

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

	

	public void clickMenuLink(String linkText) {
		waitForClickable(By.linkText(linkText)).click();
	}

	public void clickProductLink(String productName) {
		String productSlug = productName.toLowerCase().replace(" ", "-");
		WebElement product = waitForFirstDisplayed(By.cssSelector("a[href*='" + productSlug + "']"));
		wait.until(ExpectedConditions.elementToBeClickable(product)).click();
	}

	public void clickAddToCart() {
		waitForClickable(addToCartButton).click();
	}

	
	public void addProductToCart(String productName) {
		int expectedCount = getCartCount() + 1;

		clickMenuLink("Catalog");
		clickProductLink(productName);
		clickAddToCart();

		wait.until(ExpectedConditions.textToBePresentInElementLocated(badgeCount, String.valueOf(expectedCount)));
	}

	public void openCart() {
		driver.get(CART_URL);
		wait.until(ExpectedConditions.urlContains("/cart"));
		waitForVisible(pageBody);
	}



	public String getCartBadgeText() {
		return waitForVisible(badgeCount).getText().trim();
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
		waitForClickable(checkoutButton).click();
	}

	
	public String getCheckoutSummaryText() {
		wait.until(ExpectedConditions.urlContains("checkout"));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(pageBody, "Total"));
		return driver.findElement(pageBody).getText();
	}

	public int getCheckoutButtonsCount() {
		return driver.findElements(checkoutButton).size();
	}

	
	public String getEmptyCartMessage() {
		return waitForVisible(emptyMessage).getText();
	}

	public void clickRemoveItem() {
		waitForClickable(removeLink).click();
	}

	

	public void changeQuantity(int quantity) {
		setQuantityValue(String.valueOf(quantity));
		clickUpdateButtonAndWaitForReload();
	}

	public void changeQuantityToZero() {
		changeQuantity(0);
	}

	public String getQuantityValue() {
		return waitForFirstDisplayed(quantityInput).getAttribute("value");
	}

	public void setQuantityValue(String value) {
		WebElement el = waitForFirstDisplayed(quantityInput);
		el.clear();
		el.sendKeys(value);
	}

	public void clickUpdateButton() {
		waitForClickable(updateButton).click();
	}

	
	public void clickUpdateButtonAndWaitForReload() {
		WebElement button = waitForClickable(updateButton);
		button.click();
		wait.until(ExpectedConditions.stalenessOf(button));
		waitForVisible(pageBody);
	}

	public boolean isCartErrorPageShown() {
		try {
			return wait.until(ExpectedConditions.textToBePresentInElementLocated(pageBody, "Something went wrong"));
		} catch (TimeoutException e) {
			return false;
		}
	}

	public String getPageText() {
		return waitForVisible(pageBody).getText();
	}

	public void clickMiniCartToggle() {
		waitForClickable(miniCartToggle).click();
	}

	public String getMiniCartEmptyMessage() {
		return waitForVisible(miniCartEmptyMessage).getText();
	}
}