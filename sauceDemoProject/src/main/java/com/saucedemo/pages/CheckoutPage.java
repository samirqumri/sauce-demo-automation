package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
	private WebDriver driver;

	private By pageBody = By.tagName("body");
	private By orderSummaryItems = By.cssSelector(".order-summary__item, .product");
	private By removeOrder = By.xpath("//a[text()='x']");

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getOrderSummaryText() {
		return driver.findElement(pageBody).getText();
	}

	public int getOrderItemsCount() {
		return driver.findElements(orderSummaryItems).size();
	}

	public void ToRemoveOrder() {
		driver.findElement(removeOrder).click();
	}
}