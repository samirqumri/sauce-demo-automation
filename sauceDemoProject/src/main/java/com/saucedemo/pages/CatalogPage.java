package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class CatalogPage {
	private WebDriver driver;

	private By productItems = By.cssSelector(".grid__item, .product-card");
	private By productTitles = By.cssSelector(".grid-product__title, .product-card__title");
	private By soldOutBadge = By.cssSelector(".grid-product__tag--sold-out, .badge--sold-out");
	private By sortDropdown = By.cssSelector("select#SortBy, select[name='sort_by']");

	public CatalogPage(WebDriver driver) {
		this.driver = driver;
	}

	public int getProductsCount() {
		return driver.findElements(productItems).size();
	}

	public void sortBy(String visibleOption) {
		new Select(driver.findElement(sortDropdown)).selectByVisibleText(visibleOption);
	}

	public List<String> getAllProductTitles() {
		return driver.findElements(productTitles).stream().map(el -> el.getText()).toList();
	}

	public boolean hasSoldOutBadges() {
		return driver.findElements(soldOutBadge).size() > 0;
	}
}