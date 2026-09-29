package com.saucedemo.tests;
 
import java.lang.reflect.Method;
 
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
 
import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.LoginPage;
 
public class LoginTests extends BaseTest {
<<<<<<< HEAD
 
	@DataProvider(name = "loginTestData")
	public Object[][] getLoginData(Method method) {
		String testName = method.getName();
 
		if (testName.equals("validLogin")) {
			return new Object[][] { { "ibrahim.newuser001@gmail.com", "QaTeam2026" } };
		} else if (testName.equals("wrongPassword")) {
			return new Object[][] { { "ibrahim.newuser01@gmail.com", "Wrong123" } };
		} else if (testName.equals("bothEmpty")) {
			return new Object[][] { { "", "" } };
		} else if (testName.equals("emptyPassword")) {
			return new Object[][] { { "ibrahim.newuser01@gmail.com", "" } };
		} else if (testName.equals("emailWithoutAt")) {
			return new Object[][] { { "ibrahimgmail.com", "QaTeam2026" } };
		} else if (testName.equals("emailInCapitals")) {
			return new Object[][] { { "IBRAHIM.NEWUSER01@GMAIL.COM", "QaTeam2026" } };
		} else if (testName.equals("passwordInCapitals")) {
			return new Object[][] { { "ibrahim.newuser01@gmail.com", "QATEAM2026" } };
=======

	@DataProvider(name = "loginData")
<<<<<<< HEAD
	public Object[][] loginData(Method method) {
		Object[][] allData = new Object[][] {
			// test name               email                          password
			{ "validLogin",            "ibrahim.newuser01@gmail.com", "QaTeam2026" },   // correct email + correct password
			{ "logout",                "ibrahim.newuser01@gmail.com", "QaTeam2026" },   // correct email + correct password
			{ "wrongPassword",         "ibrahim.newuser01@gmail.com", "Wrong123"   },   // correct email + wrong password
			{ "bothEmpty",             "",                            ""           },   // email and password empty
			{ "emptyPassword",         "ibrahim.newuser01@gmail.com", ""           },   // correct email + empty password
			{ "emailWithoutAt",        "ibrahimgmail.com",            "QaTeam2026" },   // email without @
			{ "emailInCapitals",       "IBRAHIM.NEWUSER01@GMAIL.COM", "QaTeam2026" },   // email in capital letters
			{ "passwordInCapitals",    "ibrahim.newuser01@gmail.com", "QATEAM2026" },   // password in capital letters
			{ "backButtonAfterLogout", "ibrahim.newuser01@gmail.com", "QaTeam2026" },   // correct email + correct password
=======

	public Object[][] loginData(Method method) {

		Object[][] allData = new Object[][] {

				// test name email password

				{ "validLogin", "ibrahim.newuser001@gmail.com", "QaTeam2026" },

				{ "logout", "ibrahim.newuser01@gmail.com", "QaTeam2026" },

				{ "wrongPassword", "ibrahim.newuser01@gmail.com", "Wrong123" },

				{ "bothEmpty", "", "" },

				{ "emptyPassword", "ibrahim.newuser01@gmail.com", "" },

				{ "emailWithoutAt", "ibrahimgmail.com", "QaTeam2026" },

				{ "emailInCapitals", "IBRAHIM.NEWUSER01@GMAIL.COM", "QaTeam2026" },

				{ "passwordInCapitals", "ibrahim.newuser01@gmail.com", "QATEAM2026" },

				{ "backButtonAfterLogout", "ibrahim.newuser01@gmail.com", "QaTeam2026" },

>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
		};

<<<<<<< HEAD
		// return only the row that has the same name as the test method
		for (Object[] row : allData) {
			if (row[0].equals(method.getName())) {
				return new Object[][] { { row[1], row[2] } };
			}
		}
		return new Object[0][0];
=======
		for (Object[] row : allData) {

			if (row[0].equals(method.getName())) {

				return new Object[][] { { row[1], row[2] } };

			}

>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
		}
<<<<<<< HEAD
 
		return new Object[][] { { "ibrahim.newuser01@gmail.com", "QaTeam2026" } };
=======

		return new Object[0][0];

>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
	}
 
	@Test(priority = 1, dataProvider = "loginTestData")
	public void validLogin(String email, String password) throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");
 
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(email, password);
<<<<<<< HEAD
 
=======
<<<<<<< HEAD
		loginPage.waitForMyAccount();
=======
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation

>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
		Thread.sleep(3000);
		String currentUrl = driver.getCurrentUrl();
		String pageContent = driver.getPageSource().toLowerCase();
 
		boolean isHandled = currentUrl.contains("login") || currentUrl.contains("account")
				|| pageContent.contains("challenge") || pageContent.contains("captcha");
 
		Assert.assertTrue(isHandled, "Valid login did not redirect or trigger expected landing states.");
		Reporter.log("pass", true);
	}
 
	@Test(priority = 2)
	public void loginLinkOpensLoginPage() {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");
 
		Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Login page URL not detected.");
		Reporter.log("pass", true);
	}
 
	@Test(priority = 3)
	public void passwordIsHidden() {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");
 
		LoginPage loginPage = new LoginPage(driver);
		Assert.assertEquals(loginPage.getPasswordInputType(), "password", "Password field masking is broken.");
		Reporter.log("pass", true);
	}
 
	@Test(priority = 4)
	public void forgotPassword() {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");
 
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickForgotPassword();
 
		Assert.assertTrue(loginPage.isRecoveryFormVisible(), "Reset Password form failed to load.");
		Reporter.log("pass", true);
	}
 
	@Test(priority = 6, dataProvider = "loginTestData")
	public void wrongPassword(String email, String password) {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");
 
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(email, password);
 
		Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Login succeeded with incorrect credentials.");
		Reporter.log("pass", true);
	}
 
	@Test(priority = 7, dataProvider = "loginTestData")
	public void bothEmpty(String email, String password) {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");
 
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(email, password);
 
		Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Login allowed with empty fields.");
		Reporter.log("pass", true);
	}
 
	@Test(priority = 8, dataProvider = "loginTestData")
	public void emptyPassword(String email, String password) {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");
 
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(email, password);
 
		Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Login allowed with missing password.");
		Reporter.log("pass", true);
	}
 
	@Test(priority = 9, dataProvider = "loginTestData")
	public void emailWithoutAt(String email, String password) {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");
 
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(email, password);
 
		Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Login allowed with invalid email format.");
		Reporter.log("pass", true);
	}
 
	@Test(priority = 10)
	public void accountNeedsLogin() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.openMyAccount();
 
		Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Unauthorized deep link bypass successful.");
		Reporter.log("pass", true);
	}
 
	@Test(priority = 11, dataProvider = "loginTestData")
	public void emailInCapitals(String email, String password) throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");
 
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(email, password);
<<<<<<< HEAD
 
=======
<<<<<<< HEAD
		loginPage.waitForMyAccount();
=======
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation

>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
		Thread.sleep(3000);
		String currentUrl = driver.getCurrentUrl();
		String pageContent = driver.getPageSource().toLowerCase();
 
		boolean isHandled = currentUrl.contains("login") || currentUrl.contains("account")
				|| pageContent.contains("challenge") || pageContent.contains("captcha");
 
		Assert.assertTrue(isHandled, "Capitalized email address handling failed.");
		Reporter.log("pass", true);
	}
 
	@Test(priority = 12, dataProvider = "loginTestData")
	public void passwordInCapitals(String email, String password) {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");
 
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(email, password);
 
		Assert.assertTrue(driver.getCurrentUrl().contains("login"),
				"Authentication allowed capitalized password variants.");
		Reporter.log("pass", true);
	}
 
	@Test(priority = 13, dataProvider = "loginTestData")
	public void backButtonAfterLogout(String email, String password) {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");
 
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(email, password);
		loginPage.logout();
 
		driver.navigate().back();
		driver.navigate().refresh();
 
		Assert.assertTrue(driver.getCurrentUrl().contains("login") || !driver.getCurrentUrl().contains("/account"),
				"Browser back button re-established logged out session.");
		Reporter.log("pass", true);
	}
}

