package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AboutUsPage extends BasePage {

	private By pageContent = By.cssSelector(".page-content, .rte");
	private By pageTitle = By.tagName("h1");

	public AboutUsPage(WebDriver driver) {
		super(driver);
	}

	public String getDescriptionText() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(pageContent)).getText();
	}

	public boolean isPageTitleDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).isDisplayed();
	}
}