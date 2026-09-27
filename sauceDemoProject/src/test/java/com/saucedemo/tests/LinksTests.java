package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.saucedemo.pages.HomePage;

public class LinksTests extends BaseTest {

	@Test
	public void testFacebookLink() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		Thread.sleep(2000);

		homePage.clickFacebookLink();
		Thread.sleep(2000);

		Assert.assertTrue(driver.getPageSource().contains("facebook.com"));
		Reporter.log("pass",true);
	}

	@Test
	public void testTwitterLink() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		Thread.sleep(2000);

		homePage.clickTwitterLink();
		Thread.sleep(2000);

		Assert.assertTrue(driver.getPageSource().contains("twitter.com"));
		Reporter.log("pass",true);
	}

	@Test
	public void testInstagramLink() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		Thread.sleep(2000);

		homePage.clickInstaLink();
		Thread.sleep(2000);

		Assert.assertTrue(driver.getPageSource().contains("instagram.com"));
		Reporter.log("pass",true);
	}

	@Test
	public void testPinterestLink() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		Thread.sleep(2000);

		homePage.clickPinterestLink();
		Thread.sleep(2000);

		Assert.assertTrue(driver.getPageSource().contains("pinterest.com"));
		Reporter.log("pass",true);
	}

	@Test
	public void testAllSocialMediaLinks() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		Thread.sleep(2000);

		homePage.clickFacebookLink();
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("facebook.com"));

		homePage.clickTwitterLink();
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("twitter.com"));

		homePage.clickInstaLink();
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("instagram.com"));

		homePage.clickPinterestLink();
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("pinterest.com"));
		Reporter.log("pass",true);
	}
}
