package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
	private WebDriver driver;

	// Blank placeholder blueprint hooks for the team
	private By badgeCount = By.id("id");
	private By checkoutButton = By.name("");
	private By emptyMessage = By.className("");
	private By removeLink = By.linkText("Remove");
	private By quantityInput = By.xpath("");
	private By updateButton = By.name("");

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
