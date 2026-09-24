package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	private WebDriver driver;

	// Blank placeholder blueprint hooks for the team
	private By emailField = By.id("");
	private By passwordField = By.id("");
	private By submitButton = By.cssSelector("");
	private By errorAlert = By.className("");
	private By recoverLink = By.xpath("");
	private By recoverForm = By.id("");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void login(String email, String password) {
		driver.findElement(emailField).sendKeys(email);
		driver.findElement(passwordField).sendKeys(password);
		driver.findElement(submitButton).click();
	}

	public void clickSubmitOnly() {
		driver.findElement(submitButton).click();
	}

	public boolean isErrorMessageDisplayed() {
		return driver.findElement(errorAlert).isDisplayed();
	}

	public String getPasswordInputType() {
		return driver.findElement(passwordField).getAttribute("type");
	}

	public void clickForgotPassword() {
		driver.findElement(recoverLink).click();
	}

	public boolean isRecoveryFormVisible() {
		return driver.findElement(recoverForm).isDisplayed();
	}
}
