package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SearchPage {
	private WebDriver driver;

	// Blank placeholder blueprint hooks for the team
	private By openSearchButton = By.xpath("");
	private By searchBox = By.name("");

	public SearchPage(WebDriver driver) {
		this.driver = driver;
	}

	public void openSearchField() {
		driver.findElement(openSearchButton).click();
	}

	public void typeSearchQuery(String itemKeyword) {
		driver.findElement(searchBox).sendKeys(itemKeyword + Keys.ENTER);
	}
}
