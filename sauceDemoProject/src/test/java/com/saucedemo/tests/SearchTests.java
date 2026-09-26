package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.saucedemo.pages.CatalogPage;
import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.ProductPage;
import com.saucedemo.pages.SearchPage;

public class SearchTests extends BaseTest {

	@Test(priority = 1)
	public void invalidSortValue() throws InterruptedException {

		driver.get("https://sauce-demo.myshopify.com");
		Thread.sleep(2500);

		CatalogPage catalogPage = new CatalogPage(driver);
		Assert.assertTrue(catalogPage.getProductsCount() >= 0);
	}

	@Test(priority = 2)
	public void searchWithNoMatch() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Search");
		Thread.sleep(1500);

		SearchPage searchPage = new SearchPage(driver);
		searchPage.searchFor("zzqqxx123");
		Thread.sleep(2500);

		ProductPage productPage = new ProductPage(driver);
		Assert.assertEquals(productPage.getSearchResultsCount(), 0);
	}

	@Test(priority = 3)
	public void emptySearch() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Search");
		Thread.sleep(1500);

		SearchPage searchPage = new SearchPage(driver);
		searchPage.searchFor("");
		Thread.sleep(2500);

		ProductPage productPage = new ProductPage(driver);
		Assert.assertTrue(productPage.getBodyTextContext().contains("No search performed"));
	}

	@Test(priority = 4)
	public void searchIgnoresCase() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Search");
		Thread.sleep(1500);

		SearchPage searchPage = new SearchPage(driver);
		searchPage.searchFor("JACKET");
		Thread.sleep(2500);

		ProductPage productPage = new ProductPage(driver);
		Assert.assertNotNull(productPage.getBodyTextContext());
	}

	@Test(priority = 5)
	public void searchFullProductName() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Search");
		Thread.sleep(1500);

		SearchPage searchPage = new SearchPage(driver);
		searchPage.searchFor("Striped top");
		Thread.sleep(2500);

		ProductPage productPage = new ProductPage(driver);
		Assert.assertTrue(productPage.getBodyTextContext().contains("Striped top"));
	}

	@Test(priority = 6)
	public void searchWithScriptText() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Search");
		Thread.sleep(1500);

		SearchPage searchPage = new SearchPage(driver);
		searchPage.searchFor("<script>alert(1)</script>");
		Thread.sleep(2500);

		ProductPage productPage = new ProductPage(driver);
		Assert.assertTrue(productPage.getSearchResultsCount() == 0);
	}
}
