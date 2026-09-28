package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BlogPage extends BasePage {

	private By postTitles = By.cssSelector(".blog-post__title, article h2 a");
	private By postDates = By.cssSelector(".blog-post__date, .date");

	public BlogPage(WebDriver driver) {
		super(driver);
	}

	public int getPostsCount() {
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(postTitles)).size();
	}

	public void openPost(String title) {
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText(title))).click();
	}

	public boolean isDateDisplayedForFirstPost() {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(postDates));
		return true;
	}
}