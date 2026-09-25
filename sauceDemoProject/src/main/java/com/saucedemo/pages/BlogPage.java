package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BlogPage {
	private WebDriver driver;

	private By postTitles = By.cssSelector(".blog-post__title, article h2 a");
	private By postDates = By.cssSelector(".blog-post__date, .date");

	public BlogPage(WebDriver driver) {
		this.driver = driver;
	}

	public int getPostsCount() {
		return driver.findElements(postTitles).size();
	}

	public void openPost(String title) {
		driver.findElement(By.linkText(title)).click();
	}

	public boolean isDateDisplayedForFirstPost() {
		return driver.findElements(postDates).size() > 0;
	}
}