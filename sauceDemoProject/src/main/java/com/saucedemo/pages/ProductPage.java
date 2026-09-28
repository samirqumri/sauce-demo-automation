package com.saucedemo.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {

	private By titleText = By.xpath("(//h1)[last()]");
	private By priceText = By.cssSelector("[class*='price']");
	private By addToCartButton = By.xpath(
			"//*[self::button or self::input][contains(text(),'Add to Cart') or contains(@value,'Add to Cart')]");
	private By addToCartButtonById = By.id("add");
	private By gridItems = By.cssSelector(".grid__item, .product-card");
	private By pageBody = By.tagName("body");
	private By relatedProductsSection = By.id("related-products");

	public ProductPage(WebDriver driver) {
		super(driver);
	}

	public void clickProductLink(String productName) {
		String productSlug = productName.toLowerCase().replace(" ", "-");
		List<WebElement> matches = wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("a[href*='" + productSlug + "']")));

		for (WebElement el : matches) {
			if (el.isDisplayed()) {
				el.click();
				return;
			}
		}
	}

	public boolean areProductElementsVisible() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(titleText)).isDisplayed()
				&& wait.until(ExpectedConditions.visibilityOfElementLocated(priceText)).isDisplayed()
				&& wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton)).isDisplayed();
	}

	public String getProductName() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(titleText)).getText();
	}

	public String getProductPrice() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(priceText)).getText();
	}

	public boolean isAddToCartEnabled() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton)).isEnabled();
	}

	public void clickAddToCart() {
		wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
	}

	public boolean isProductSoldOut() {
		return !wait.until(ExpectedConditions.presenceOfElementLocated(addToCartButtonById)).isEnabled();
	}

	public int getSearchResultsCount() {
		try {
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(gridItems)).size();
		} catch (org.openqa.selenium.TimeoutException e) {
			return 0;
		}
	}

	public String getBodyTextContext() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(pageBody)).getText();
	}

	public String getAddToCartButtonText() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton)).getText().toLowerCase();
	}

	public String getRelatedProductsText() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(relatedProductsSection)).getText();
	}

	public void clickRelatedProduct(String productName) {
		String productSlug = productName.toLowerCase().replace(" ", "-");
		WebElement section = wait.until(ExpectedConditions.visibilityOfElementLocated(relatedProductsSection));
		wait.until(ExpectedConditions
				.elementToBeClickable(section.findElement(By.cssSelector("a[href*='" + productSlug + "']")))).click();
	}

}