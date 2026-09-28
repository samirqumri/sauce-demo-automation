package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignUpPage extends BasePage {
<<<<<<< HEAD
	
=======
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation

	private By firstNameField = By.xpath("//label[normalize-space()='First Name']/following::input[1]");
	private By lastNameField = By.xpath("//label[normalize-space()='Last Name']/following::input[1]");
	private By emailField = By.xpath("//label[normalize-space()='Email Address']/following::input[1]");
	private By passwordField = By.xpath("//label[normalize-space()='Password']/following::input[1]");
	private By createButton = By.xpath("//form[.//label[normalize-space()='First Name']]//*[@type='submit']");
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
<<<<<<< HEAD
		return driver.findElements(errorMessage).size() > 0;
=======
		try {
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(errorMessage));
			return true;
		} catch (org.openqa.selenium.TimeoutException e) {
			return false;
		}
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
	}

	public String getErrorText() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
	}

<<<<<<< HEAD

	public void waitForAccountCreated() {
		wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("register")));
	}


	public void waitForErrorMessage() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
	}
=======
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
}