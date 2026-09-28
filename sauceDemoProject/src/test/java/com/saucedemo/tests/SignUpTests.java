package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.SignUpPage;

public class SignUpTests extends BaseTest {

	

	@DataProvider(name = "signUpData") public Object[][] signUpData() { 
		return new Object[][] {
		
			{"Ali", "Test", "createAccount",         "ibrahim.newuser04@gmail.com", "QaTeam2026" },   // NEW email + correct password (change the number every run)
			{"Ali", "Test", "emptyEmailAndPassword", "",                            ""           },   // email and password empty
			{"Ali", "Test", "emailWithoutAt",        "ibrahimgmail.com",            "QaTeam2026" },   // email without @
			{ "Ali", "Test","existingEmail",         "ibrahim.newuser01@gmail.com", "QaTeam2026" },   // email that already has an account
			{ "Ali", "Test","shortPassword",         "ibrahim.short01@gmail.com",   "Ab1c"       },   // password with 4 characters
		};

	
	}



	@Test(priority = 1, dataProvider = "signUpData")
	public void createAccount(String firstName,String LasttName,String email, String password) {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Sign up");

		SignUpPage SignUp = new SignUpPage(driver);
		SignUp.signUp(firstName, LasttName, email, password);
		

		Assert.assertFalse(SignUp.isErrorDisplayed(), "Account should be created");
		Reporter.log("pass", true);
	}

	@Test(priority = 2, dataProvider = "signUpData")
	public void emptyEmailAndPassword(String firstName,String LasttName,String email, String password) {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Sign up");

		SignUpPage SignUp = new SignUpPage(driver);
		SignUp.signUp(firstName, LasttName, email, password);

		Assert.assertTrue(SignUp.isErrorDisplayed() || driver.getCurrentUrl().contains("register"),
				"Account must NOT be created (error shown or still on the Sign up page)");
		Reporter.log("pass", true);
	}

	@Test(priority = 3, dataProvider = "signUpData")
	public void emailWithoutAt(String firstName,String LasttName,String email, String password) {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Sign up");

		SignUpPage SignUp = new SignUpPage(driver);
		SignUp.signUp(firstName, LasttName, email, password);

		Assert.assertTrue(SignUp.isErrorDisplayed() || driver.getCurrentUrl().contains("register"),
				"Account must NOT be created (error shown or still on the Sign up page)");
		Reporter.log("pass", true);
	}

	@Test(priority = 4, dataProvider = "signUpData")
	public void existingEmail(String firstName,String LasttName,String email, String password) {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Sign up");

		SignUpPage SignUp = new SignUpPage(driver);
		SignUp.signUp(firstName,LasttName, email, password);
		

		Assert.assertTrue(SignUp.isErrorDisplayed(), "Error expected: email already taken");
		Reporter.log("pass", true);
	}

	@Test(priority = 5, dataProvider = "signUpData")
	public void shortPassword(String firstName,String LasttName,String email, String password) {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Sign up");

		SignUpPage SignUp = new SignUpPage(driver);
		SignUp.signUp(firstName, LasttName, email, password);
		

		Assert.assertTrue(SignUp.isErrorDisplayed(), "Error expected: password too short");
		Reporter.log("pass", true);
	}
}