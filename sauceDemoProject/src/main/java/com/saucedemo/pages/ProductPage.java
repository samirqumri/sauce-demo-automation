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
			text = text.replace("(", "").replace(")", "").trim();

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
<<<<<<< HEAD
		List<WebElement> matches = wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("a[href*='" + productSlug + "']")));
=======
		By productLink = By.cssSelector("a[href*='" + productSlug + "']");

		wait.until(ExpectedConditions.visibilityOfElementLocated(productLink));
		List<WebElement> matches = driver.findElements(productLink);
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git

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
<<<<<<< HEAD
		return wait.until(ExpectedConditions.visibilityOfElementLocated(titleText)).getText();
=======
		WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(titleText));
		return title.getText();
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git
	}

	public String getProductPrice() {
<<<<<<< HEAD
		return wait.until(ExpectedConditions.visibilityOfElementLocated(priceText)).getText();
=======
		WebElement price = wait.until(ExpectedConditions.visibilityOfElementLocated(priceText));
		return price.getText();
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git
	}

	public boolean isAddToCartEnabled() {
<<<<<<< HEAD
		return wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton)).isEnabled();
=======
		WebElement addButton = wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
		return addButton.isEnabled();
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git
	}

	public void clickAddToCart() {
<<<<<<< HEAD
		wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
=======
		int expectedCount = getCartCount() + 1;

		WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
		addButton.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='cart-target-desktop' and contains(., '(" + expectedCount + ")')]")));
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git
	}

	public boolean isProductSoldOut() {
<<<<<<< HEAD
		return !wait.until(ExpectedConditions.presenceOfElementLocated(addToCartButtonById)).isEnabled();
=======
		WebElement addButton = wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButtonById));
		return !addButton.isEnabled();
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git
	}

	public int getSearchResultsCount() {
		try {
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(gridItems)).size();
		} catch (org.openqa.selenium.TimeoutException e) {
			return 0;
		}
	}

	public String getBodyTextContext() {
<<<<<<< HEAD
		return wait.until(ExpectedConditions.visibilityOfElementLocated(pageBody)).getText();
=======
		WebElement body = wait.until(ExpectedConditions.visibilityOfElementLocated(pageBody));
		return body.getText();
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git
	}

	public String getAddToCartButtonText() {
<<<<<<< HEAD
		return wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton)).getText().toLowerCase();
=======
		WebElement addButton = wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
		return addButton.getText().toLowerCase();
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git
	}

	public String getRelatedProductsText() {
<<<<<<< HEAD
		return wait.until(ExpectedConditions.visibilityOfElementLocated(relatedProductsSection)).getText();
=======
		WebElement related = wait.until(ExpectedConditions.visibilityOfElementLocated(relatedProductsSection));
		return related.getText();
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git
	}

	public void clickRelatedProduct(String productName) {
		String productSlug = productName.toLowerCase().replace(" ", "-");
<<<<<<< HEAD
		WebElement section = wait.until(ExpectedConditions.visibilityOfElementLocated(relatedProductsSection));
		wait.until(ExpectedConditions
				.elementToBeClickable(section.findElement(By.cssSelector("a[href*='" + productSlug + "']")))).click();
=======
		WebElement link = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("#related-products a[href*='" + productSlug + "']")));
		link.click();
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation.git
	}

}