package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
	private WebDriver driver;

	private By firstNameField = By.id("RegisterForm-FirstName");
	private By lastNameField = By.id("RegisterForm-LastName");
	private By emailField = By.id("RegisterForm-email");
	private By passwordField = By.id("RegisterForm-password");
	private By createButton = By.cssSelector("button[type='submit']");
	private By errorMessage = By.cssSelector(".form__message, .error");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
	}

	public void signUp(String firstName, String lastName, String email, String password) {
		driver.findElement(firstNameField).sendKeys(firstName);
		driver.findElement(lastNameField).sendKeys(lastName);
		driver.findElement(emailField).sendKeys(email);
		driver.findElement(passwordField).sendKeys(password);
		driver.findElement(createButton).click();
	}

	public boolean isErrorDisplayed() {
		return driver.findElements(errorMessage).size() > 0;
	}

	public String getErrorText() {
		return driver.findElement(errorMessage).getText();
	}
}