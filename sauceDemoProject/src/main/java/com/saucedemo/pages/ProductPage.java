package com.saucedemo.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage {
	private WebDriver driver;

	private By titleText = By.xpath("(//h1)[last()]");
	private By priceText = By.cssSelector("[class*='price']");
	private By addToCartButton = By.xpath(
			"//*[self::button or self::input][contains(text(),'Add to Cart') or contains(@value,'Add to Cart')]");
	private By addToCartButtonById = By.id("add");
	private By gridItems = By.cssSelector(".grid__item, .product-card");
	private By pageBody = By.tagName("body");
	private By relatedProductsSection = By.id("related-products");

	public ProductPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickProductLink(String productName) {
		String productSlug = productName.toLowerCase().replace(" ", "-");
		List<WebElement> matches = driver.findElements(By.cssSelector("a[href*='" + productSlug + "']"));

		for (WebElement el : matches) {
			if (el.isDisplayed()) {
				el.click();
				return;
			}
		}
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

	public boolean isProductSoldOut() {
		return !driver.findElement(addToCartButtonById).isEnabled();
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
	

	public String getRelatedProductsText() {
		return driver.findElement(relatedProductsSection).getText();
	}

	public void clickRelatedProduct(String productName) {
		String productSlug = productName.toLowerCase().replace(" ", "-");
		driver.findElement(relatedProductsSection)
				.findElement(By.cssSelector("a[href*='" + productSlug + "']")).click();
	}
}