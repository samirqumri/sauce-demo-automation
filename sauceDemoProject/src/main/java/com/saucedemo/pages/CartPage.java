package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
	private WebDriver driver;

	private By badgeCount = By.cssSelector("a[href='/cart']");
	private By checkoutButton = By.name("checkout");
	private By emptyMessage = By.cssSelector(".rte p, div.errors, #main p");
	private By removeLink = By.linkText("Remove");
	private By quantityInput = By.cssSelector("input.cart__qty-input, input[name='updates[]']");
	private By updateButton = By.cssSelector("button.cart__update, input[name='update']");

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
		driver.findElement(quantityInput).clear();
		driver.findElement(quantityInput).sendKeys("0");
		driver.findElement(updateButton).click();
	}
}
