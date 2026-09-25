package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
	private WebDriver driver;

	private By titleText = By.xpath("(//h1)[last()]");
	private By priceText = By.cssSelector("[class*='price']");
	private By addToCartButton = By.xpath(
		    "//*[self::button or self::input][contains(text(),'Add to Cart') or contains(@value,'Add to Cart')]");
	private By gridItems = By.cssSelector(".grid__item, .product-card");
	private By pageBody = By.tagName("body");

	public ProductPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickProductLink(String productName) {
	    driver.findElement(By.partialLinkText(productName)).click();
	}
	public boolean areProductElementsVisible() {
		return driver.findElement(titleText).isDisplayed() && driver.findElement(priceText).isDisplayed()
				&& driver.findElement(addToCartButton).isDisplayed();
	}

	public String getProductName() {
		return driver.findElement(titleText).getText();
	}

	public String getProductPrice() {
		return driver.findElement(priceText).getText();
	}

	public boolean isAddToCartEnabled() {
		return driver.findElement(addToCartButton).isEnabled();
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