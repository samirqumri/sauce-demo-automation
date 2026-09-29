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
	private By badgeCount = By.id("cart-target-desktop");

	public ProductPage(WebDriver driver) {
		super(driver);
	}

	public void clickMenuLink(String linkText) {
		WebElement menuLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(linkText)));
		menuLink.click();
	}

	public int getCartCount() {
		try {
			String text = driver.findElement(badgeCount).getText();
			text = text.replaceAll("[()]","").trim();

			if (text.isEmpty()) {
				return 0;
			}

			return Integer.parseInt(text);

		} catch (org.openqa.selenium.NoSuchElementException e) {
			return 0;
		}
	}

	public void clickProductLink(String productName) {
		String productSlug = productName.toLowerCase().replace(" ", "-");

		By productLink = By.cssSelector("a[href*='" + productSlug + "']");

		wait.until(ExpectedConditions.visibilityOfElementLocated(productLink));
		List<WebElement> matches = driver.findElements(productLink);

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

		WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(titleText));
		return title.getText();
	}

	public String getProductPrice() {

		WebElement price = wait.until(ExpectedConditions.visibilityOfElementLocated(priceText));
		return price.getText();
	}

	public boolean isAddToCartEnabled() {

		WebElement addButton = wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
		return addButton.isEnabled();
	}

	public void clickAddToCart() {

		int expectedCount = getCartCount() + 1;

		WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
		addButton.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='cart-target-desktop' and contains(., '(" + expectedCount + ")')]")));
	}

	public boolean isProductSoldOut() {

		WebElement addButton = wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButtonById));
		return !addButton.isEnabled();
	}

	public int getSearchResultsCount() {
		return driver.findElements(gridItems).size();
	}

	public String getBodyTextContext() {

		WebElement body = wait.until(ExpectedConditions.visibilityOfElementLocated(pageBody));
		return body.getText();
	}

	public String getAddToCartButtonText() {

		WebElement addButton = wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
		return addButton.getText().toLowerCase();
	}

	public String getRelatedProductsText() {

		WebElement related = wait.until(ExpectedConditions.visibilityOfElementLocated(relatedProductsSection));
		return related.getText();
	}

	public void clickRelatedProduct(String productName) {
		String productSlug = productName.toLowerCase().replace(" ", "-");

		WebElement link = wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("#related-products a[href*='" + productSlug + "']")));
		link.click();
	}
}