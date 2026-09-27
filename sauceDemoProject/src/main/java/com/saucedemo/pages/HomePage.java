package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	private WebDriver driver;

	private By storeName = By.cssSelector(".header__logo, .site-header__logo");
	private By tagline = By.cssSelector(".hero__subheading, .tagline");


	private By pageBody = By.tagName("body");


	private By facebooklink = By.cssSelector("a[href*='facebook']");
	private By twitterlink = By.cssSelector("a[href*='twitter']");
	private By instalink = By.cssSelector("a[href*='instagram']");
	private By pinterestlink = By.cssSelector("a[href*='pinterest']");


	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isStoreNameDisplayed() {
		return driver.findElement(storeName).isDisplayed();
	}

	public String getTaglineText() {
		return driver.findElement(tagline).getText();
	}

	public void clickMenuLink(String linkText) {
		driver.findElement(By.linkText(linkText)).click();
	}


	public String getPageText() {
		return driver.findElement(pageBody).getText();
	}

	public void clickFeaturedProduct(String productName) {
		String productSlug = productName.toLowerCase().replace(" ", "-");

		driver.findElement(By.cssSelector("a[href*='" + productSlug + "']")).click();

		driver.findElement(By.cssSelector("a[href*='" + productSlug + "']")).click();
	}


	public void clickFacebookLink() {
		driver.findElement(facebooklink).click();
	}

	public void clickTwitterLink() {
		driver.findElement(twitterlink).click();
	}

	public void clickInstaLink() {
		driver.findElement(instalink).click();
	}

	public void clickPinterestLink() {
		driver.findElement(pinterestlink).click();

	}
}