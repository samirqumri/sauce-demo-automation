package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SearchPage extends BasePage {
	private WebDriver driver;
	private By searchBox = By.id("search-field");

	public SearchPage(WebDriver driver) {
		super(driver);
	}

	public void searchFor(String itemKeyword) {
		driver.findElement(searchBox).click();
		driver.findElement(searchBox).sendKeys(itemKeyword + Keys.ENTER);
	}
}
