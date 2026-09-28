package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AboutUsPage extends BasePage {
	private WebDriver driver;

	private By pageContent = By.cssSelector(".page-content, .rte");
	private By pageTitle = By.tagName("h1");

	public AboutUsPage(WebDriver driver) {
		super(driver);
	}

	public String getDescriptionText() {
		return driver.findElement(pageContent).getText();
	}

	public boolean isPageTitleDisplayed() {
		return driver.findElement(pageTitle).isDisplayed();
	}
}