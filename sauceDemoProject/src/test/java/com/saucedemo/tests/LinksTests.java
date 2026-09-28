package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.saucedemo.pages.HomePage;

public class LinksTests extends BaseTest {

	@Test
	public void testFacebookLink() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		

		homePage.clickFacebookLink();
		

		Assert.assertTrue(driver.getPageSource().contains("facebook.com"));
		Reporter.log("pass",true);
	}

	@Test
	public void testTwitterLink() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		

		homePage.clickTwitterLink();
		

		Assert.assertTrue(driver.getPageSource().contains("twitter.com"));
		Reporter.log("pass",true);
	}

	@Test
	public void testInstagramLink() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		

		homePage.clickInstaLink();

		Assert.assertTrue(driver.getPageSource().contains("instagram.com"));
		Reporter.log("pass",true);
	}

	@Test
	public void testPinterestLink() throws InterruptedException {
		HomePage homePage = new HomePage(driver);

		homePage.clickPinterestLink();

		Assert.assertTrue(driver.getPageSource().contains("pinterest.com"));
		Reporter.log("pass",true);
	}

	@Test
	public void testAllSocialMediaLinks() throws InterruptedException {
		HomePage homePage = new HomePage(driver);

		homePage.clickFacebookLink();
		Assert.assertTrue(driver.getPageSource().contains("facebook.com"));

		homePage.clickTwitterLink();
		Assert.assertTrue(driver.getPageSource().contains("twitter.com"));

		homePage.clickInstaLink();
		Assert.assertTrue(driver.getPageSource().contains("instagram.com"));

		homePage.clickPinterestLink();
		Assert.assertTrue(driver.getPageSource().contains("pinterest.com"));
		Reporter.log("pass",true);
	}
}
