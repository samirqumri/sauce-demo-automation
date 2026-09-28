package com.saucedemo.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CatalogPage extends BasePage {

	private By productItems = By.cssSelector(".grid__item, .product-card");
	private By productTitles = By.cssSelector(".grid-product__title, .product-card__title");
	private By soldOutBadge = By.cssSelector(".grid-product__tag--sold-out, .badge--sold-out");
	private By sortDropdown = By.cssSelector("select#SortBy, select[name='sort_by']");

	public CatalogPage(WebDriver driver) {
		super(driver);
	}

	public int getProductsCount() {
		try {
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productItems)).size();
		} catch (org.openqa.selenium.TimeoutException e) {
			return 0;
		}
	}

	public void sortBy(String visibleOption) {
		WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(sortDropdown));
		new Select(dropdown).selectByVisibleText(visibleOption);
	}

	public List<String> getAllProductTitles() {
		List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productTitles));
		return elements.stream().map(el -> el.getText()).toList();
	}

	public boolean hasSoldOutBadges() {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(soldOutBadge));
		return true;
	}
}