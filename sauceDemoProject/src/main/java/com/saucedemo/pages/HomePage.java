package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

	private By storeName = By.cssSelector(".header__logo, .site-header__logo");
	private By tagline = By.cssSelector(".hero__subheading, .tagline");

	private By pageBody = By.tagName("body");

	private By facebooklink = By.cssSelector("a[href*='facebook']");
	private By twitterlink = By.cssSelector("a[href*='twitter']");
	private By instalink = By.cssSelector("a[href*='instagram']");
	private By pinterestlink = By.cssSelector("a[href*='pinterest']");

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public boolean isStoreNameDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(storeName)).isDisplayed();
	}

	public String getTaglineText() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(tagline)).getText();
	}

	public void clickMenuLink(String linkText) {
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText(linkText))).click();
	}

	public String getPageText() {
		wait.until(ExpectedConditions.textToBePresentInElementLocated(pageBody, "Sauce"));
		return driver.findElement(pageBody).getText();
	}

	public void clickFeaturedProduct(String productName) {
		String productSlug = productName.toLowerCase().replace(" ", "-");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='" + productSlug + "']"))).click();
	}

	public void clickFacebookLink() {
		wait.until(ExpectedConditions.elementToBeClickable(facebooklink)).click();
	}

	public void clickTwitterLink() {
		wait.until(ExpectedConditions.elementToBeClickable(twitterlink)).click();
	}

	public void clickInstaLink() {
		wait.until(ExpectedConditions.elementToBeClickable(instalink)).click();
	}

	public void clickPinterestLink() {
		wait.until(ExpectedConditions.elementToBeClickable(pinterestlink)).click();
	}

}