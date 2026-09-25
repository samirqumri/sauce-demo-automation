package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	private WebDriver driver;

	private By storeName = By.cssSelector(".header__logo, .site-header__logo");
	private By tagline = By.cssSelector(".hero__subheading, .tagline");
	private By featuredProducts = By.cssSelector(".featured-products .grid__item, .product-card");

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isStoreNameDisplayed() {
		return driver.findElement(storeName).isDisplayed();
	}

	public String getTaglineText() {
		return driver.findElement(tagline).getText();
	}

	public int getFeaturedProductsCount() {
		return driver.findElements(featuredProducts).size();
	}

	public void clickMenuLink(String linkText) {
		driver.findElement(By.linkText(linkText)).click();
	}
}