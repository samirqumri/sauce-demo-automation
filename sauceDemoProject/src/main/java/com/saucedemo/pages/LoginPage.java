package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

	private By emailField = By.id("customer_email");
	private By passwordField = By.id("customer_password");
	private By submitButton = By.cssSelector("input[value='Sign In']");
	private By errorAlert = By.cssSelector(".errors, .errors li, .alert-error");
	private By recoverLink = By.xpath("//a[text()='Forgot your password?']");
	private By recoverForm = By.id("recover-email");

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void login(String email, String password) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
		wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
		wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
	}

	public void clickSubmitOnly() {
		wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
	}

	public boolean isErrorMessageDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(errorAlert)).isDisplayed();
	}

	public String getPasswordInputType() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).getAttribute("type");
	}

	public void clickForgotPassword() {
		wait.until(ExpectedConditions.elementToBeClickable(recoverLink)).click();
	}

	public boolean isRecoveryFormVisible() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(recoverForm)).isDisplayed();
	}

	public void logout() {
		driver.get("https://sauce-demo.myshopify.com/account/logout");
	}

	public void openMyAccount() {
		driver.get("https://sauce-demo.myshopify.com/account");
	}

	public void waitForLoginPage() {
		wait.until(ExpectedConditions.urlContains("account/login"));
	}

	public void waitForMyAccount() {
		wait.until(ExpectedConditions.urlToBe("https://sauce-demo.myshopify.com/account"));
	}

	public void waitForErrorMessage() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(errorAlert));
	}

}