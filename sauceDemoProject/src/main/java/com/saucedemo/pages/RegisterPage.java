package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {
	private WebDriver driver;

	// Each box is found by the text you SEE above it on the page
	private By firstNameField = By.xpath("//label[normalize-space()='First Name']/following::input[1]");
	private By lastNameField = By.xpath("//label[normalize-space()='Last Name']/following::input[1]");
	private By emailField = By.xpath("//label[normalize-space()='Email Address']/following::input[1]");
	private By passwordField = By.xpath("//label[normalize-space()='Password']/following::input[1]");
	private By createButton = By.xpath("//form[.//label[normalize-space()='First Name']]//*[@type='submit']");
	private By errorMessage = By.cssSelector(".errors, .error, .form-error");

	public RegisterPage(WebDriver driver) {
		super(driver);
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