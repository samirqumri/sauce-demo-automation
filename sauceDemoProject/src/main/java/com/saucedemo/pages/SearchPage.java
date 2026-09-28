package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPage extends BasePage {
	private By searchBox = By.id("search-field");

	public SearchPage(WebDriver driver) {
		super(driver);
	}

	public void searchFor(String itemKeyword) {
		WebElement search = wait.until(ExpectedConditions.elementToBeClickable(searchBox));
		search.click();
		search.sendKeys(itemKeyword + Keys.ENTER);
	}

}
