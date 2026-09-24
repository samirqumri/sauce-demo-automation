package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
	private WebDriver driver;

	// Blank placeholder blueprint hooks for the team
	private By titleText = By.className("");
	private By priceText = By.className("");
	private By addToCartButton = By.name("");
	private By gridItems = By.className("");
	private By pageBody = By.tagName("");

	public ProductPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickProductLink(String productName) {
		driver.findElement(By.linkText(productName)).click();
	}

	public boolean areProductElementsVisible() {
		return driver.findElement(titleText).isDisplayed() && driver.findElement(priceText).isDisplayed()
				&& driver.findElement(addToCartButton).isDisplayed();
	}

	public void clickAddToCart() {
		driver.findElement(addToCartButton).click();
	}

	public int getSearchResultsCount() {
		return driver.findElements(gridItems).size();
	}

	public String getBodyTextContext() {
		return driver.findElement(pageBody).getText();
	}

	public String getAddToCartButtonText() {
		return driver.findElement(addToCartButton).getText().toLowerCase();
	}
}
