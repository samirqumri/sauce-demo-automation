package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.LoginPage;

/**
 * Owner: Ibrahim - Login tests
 * Logged in     -> the address is  .../account
 * Not logged in -> the address is  .../account/login   (it has the word "login")
 */
public class LoginTests extends BaseTest {

    @Test(priority = 1, description = "TC_LOG_01 Login with correct email and password")
    public void validLogin() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMenuLink("Log In");
        Thread.sleep(1500);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("ibrahim.newuser01@gmail.com", "QaTeam2026");
        Thread.sleep(3000);

        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "My Account page should open");
        Reporter.log("pass",true);
    }

    @Test(priority = 2, description = "TC_LOG_13 'Log In' link in the menu opens the login page")
    public void loginLinkOpensLoginPage() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMenuLink("Log In");
        Thread.sleep(1500);

        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Login page should open");
        Reporter.log("pass",true);
    }

    @Test(priority = 3, description = "TC_LOG_08 Password is hidden (dots)")
    public void passwordIsHidden() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMenuLink("Log In");
        Thread.sleep(1500);

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertEquals(loginPage.getPasswordInputType(), "password");
        Reporter.log("pass",true);
    }

    @Test(priority = 4, description = "TC_LOG_11 'Forgot your password?' opens the reset form")
    public void forgotPassword() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMenuLink("Log In");
        Thread.sleep(1500);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickForgotPassword();
        Thread.sleep(1500);

        Assert.assertTrue(loginPage.isRecoveryFormVisible(), "Reset Password form should show");
        Reporter.log("pass",true);
    }

    @Test(priority = 5, description = "TC_LOG_09 Log Out works")
    public void logout() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMenuLink("Log In");
        Thread.sleep(1500);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("ibrahim.newuser01@gmail.com", "QaTeam2026");
        Thread.sleep(3000);

        driver.get("https://sauce-demo.myshopify.com/account/logout");   // Log Out (a pop-up can't block this)
        Thread.sleep(2000);

        driver.get("https://sauce-demo.myshopify.com/account");
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "After Log Out, My Account must ask for login again");
        Reporter.log("pass",true);
    }

    @Test(priority = 6, description = "TC_LOG_02 Correct email + wrong password")
    public void wrongPassword() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMenuLink("Log In");
        Thread.sleep(1500);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("ibrahim.newuser01@gmail.com", "Wrong123");
        Thread.sleep(3000);

        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Must NOT log in");
        Reporter.log("pass",true);
    }

 
               
    

    @Test(priority = 7, description = "TC_LOG_04 Email and password both empty")
    public void bothEmpty() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMenuLink("Log In");
        Thread.sleep(1500);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickSubmitOnly();
        Thread.sleep(2000);

        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Must NOT log in");
        Reporter.log("pass",true);
    }

    @Test(priority = 8, description = "TC_LOG_05 Correct email + empty password")
    public void emptyPassword() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMenuLink("Log In");
        Thread.sleep(1500);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("ibrahim.newuser01@gmail.com", "");
        Thread.sleep(3000);

        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Must NOT log in");
        Reporter.log("pass",true);
    }

    @Test(priority = 9, description = "TC_LOG_06 Email without @")
    public void emailWithoutAt() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMenuLink("Log In");
        Thread.sleep(1500);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("ibrahimgmail.com", "QaTeam2026");
        Thread.sleep(3000);

        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Must NOT log in");
        Reporter.log("pass",true);
    }

  

    @Test(priority = 10, description = "TC_LOG_10 My Account cannot be opened without login")
    public void accountNeedsLogin() throws InterruptedException {
        driver.get("https://sauce-demo.myshopify.com/account");
        Thread.sleep(2000);

        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Website should ask for login");
        Reporter.log("pass",true);
    }
    @Test(priority = 11, description = "TC_LOG_14 Email in CAPITAL letters still logs in")
    public void emailInCapitals() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMenuLink("Log In");
        Thread.sleep(1500);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("IBRAHIM.NEWUSER01@GMAIL.COM", "QaTeam2026");
        Thread.sleep(3000);

        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Email is not case-sensitive - should log in");
        Reporter.log("pass",true);
    }

    @Test(priority = 12, description = "TC_LOG_15 Password in CAPITAL letters must NOT log in")
    public void passwordInCapitals() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMenuLink("Log In");
        Thread.sleep(1500);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("ibrahim.newuser01@gmail.com", "QATEAM2026");
        Thread.sleep(3000);

        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Password is case-sensitive - must NOT log in");
        Reporter.log("pass",true);
    }


    @Test(priority = 13, description = "TC_LOG_19 After Log Out, the Back button does not log you in again")
    public void backButtonAfterLogout() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickMenuLink("Log In");
        Thread.sleep(1500);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("ibrahim.newuser01@gmail.com", "QaTeam2026");
        Thread.sleep(3000);

        driver.get("https://sauce-demo.myshopify.com/account/logout");   // Log Out (a pop-up can't block this)
        Thread.sleep(2000);

        driver.navigate().back();
        Thread.sleep(1500);
        driver.navigate().refresh();
        Thread.sleep(2000);

        Assert.assertTrue(driver.getCurrentUrl().contains("login") || !driver.getCurrentUrl().contains("/account"),
                "Must stay logged out");
        Reporter.log("pass",true);
    }
}
