package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignUpPage extends BasePage {

	private By firstNameField = By.xpath("//input[@id='first_name']");
	private By lastNameField  = By.xpath("//input[@id='last_name']");
	private By emailField     = By.xpath("//input[@id='email']");
	private By passwordField  = By.xpath("//input[@id='password']");
	private By createButton = By.cssSelector("#create_customer [type='submit']");
	private By errorMessage = By.cssSelector(".errors, .error, .form-error");

	public SignUpPage(WebDriver driver) {
		super(driver);
	}

	public void signUp(String firstName, String lastName, String email, String password) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(firstName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField)).sendKeys(lastName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
		wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
		wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();
	}

	public boolean isErrorDisplayed() {
		try {
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(errorMessage));
			return true;
		} catch (org.openqa.selenium.TimeoutException e) {
			return false;
		}
	}

	public String getErrorText() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
	}

	public void waitForAccountCreated() {
		wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("register")));
	}

	public void waitForErrorMessage() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
	}
}