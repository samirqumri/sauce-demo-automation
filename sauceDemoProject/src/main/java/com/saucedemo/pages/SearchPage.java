package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SearchPage {
	private WebDriver driver;

	// Contains 2 methods in 1
	// 1. Open search field
	// 2. Type Search Query
	private By searchBox = By.id("search-field");

	public SearchPage(WebDriver driver) {
		this.driver = driver;
	}

	public void searchFor(String itemKeyword) {
		driver.findElement(searchBox).click();
		driver.findElement(searchBox).sendKeys(itemKeyword + Keys.ENTER);
	}
}
