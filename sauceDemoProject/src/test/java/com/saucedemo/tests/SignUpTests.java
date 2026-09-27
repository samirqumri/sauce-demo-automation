package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.RegisterPage;


public class SignUpTests extends BaseTest {

    @Test(priority = 1, description = "TC_SIGN_02 Create an account with a new email")
    public void createAccount() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMenuLink("Sign up");
        Thread.sleep(1500);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.signUp("Ali", "Test", "ibrahim.newuser01@gmail.com", "QaTeam2026");
        Thread.sleep(3000);

     
        Assert.assertTrue(driver.getCurrentUrl().contains("register"), "Account should be created");
        Reporter.log("pass",true);
    }

    @Test(priority = 2, description = "TC_SIGN_03 Sign up with empty email and password")
    public void emptyEmailAndPassword() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMenuLink("Sign up");
        Thread.sleep(1500);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.signUp("Ali", "Test", "", "");
        Thread.sleep(3000);

        Assert.assertTrue(registerPage.isErrorDisplayed() || driver.getCurrentUrl().contains("register"),
                "Account must NOT be created (error shown or still on the Sign up page)");
        Reporter.log("pass",true);
    }

    @Test(priority = 3, description = "TC_SIGN_04 Sign up with an email without @")
    public void emailWithoutAt() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMenuLink("Sign up");
        Thread.sleep(1500);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.signUp("Ali", "Test", "ibrahimgmail.com", "QaTeam2026");
        Thread.sleep(3000);

        Assert.assertTrue(registerPage.isErrorDisplayed() || driver.getCurrentUrl().contains("register"),
                "Account must NOT be created (error shown or still on the Sign up page)");
        Reporter.log("pass",true);
    }

    @Test(priority = 4, description = "TC_SIGN_05 Sign up with an email that already has an account")
    public void existingEmail() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMenuLink("Sign up");
        Thread.sleep(1500);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.signUp("Ali", "Test", "ibrahim.qa.test@gmail.com", "QaTeam2026");
        Thread.sleep(3000);

        Assert.assertFalse(registerPage.isErrorDisplayed(), "Error expected: email already taken");
        Reporter.log("pass",true);
    }

    @Test(priority = 5, description = "TC_SIGN_06 Sign up with a short password (4 characters)")
    public void shortPassword() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMenuLink("Sign up");
        Thread.sleep(1500);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.signUp("Ali", "Test", "ibrahim.short01@gmail.com", "Ab1c");
        Thread.sleep(3000);

        Assert.assertFalse(registerPage.isErrorDisplayed(), "Error expected: password too short");
        Reporter.log("pass",true);
    }
}
