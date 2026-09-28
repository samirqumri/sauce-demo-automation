package com.saucedemo.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.ProductPage;

public class CheckoutTests extends BaseTest {

    @Test(priority = 1)
    public void checkoutWithAllFieldsEmptyShowsErrors()
            throws InterruptedException {

        HomePage homePage = new HomePage(driver);

        homePage.clickMenuLink("Catalog");

       

        ProductPage productPage = new ProductPage(driver);

        productPage.clickProductLink("Grey jacket");

    

        productPage.clickAddToCart();

        

        driver.get("https://sauce-demo.myshopify.com/cart");

      

        CartPage cartPage = new CartPage(driver);

        cartPage.clickCheckout();

       

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.clickPayNow();

       

        Assert.assertTrue(
                checkoutPage.getEmailErrorText()
                        .contains("Enter an email")
        );

        Assert.assertTrue(
                driver.getCurrentUrl().contains("checkout")
        );
    }


    @Test(priority = 2)
    public void checkoutWithInvalidEmailShowsError()
            throws InterruptedException {

        HomePage homePage = new HomePage(driver);

        homePage.clickMenuLink("Catalog");

       

        ProductPage productPage = new ProductPage(driver);

        productPage.clickProductLink("Grey jacket");

        

        productPage.clickAddToCart();

       

        driver.get("https://sauce-demo.myshopify.com/cart");

       

        CartPage cartPage = new CartPage(driver);

        cartPage.clickCheckout();

        

        driver.findElement(By.id("email"))
                .sendKeys("not-an-email");

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.clickPayNow();

        

        Assert.assertTrue(
                checkoutPage.getEmailErrorText()
                        .contains("valid email")
        );

        Assert.assertTrue(
                driver.getCurrentUrl().contains("checkout")
        );
    }


    @Test(priority = 3)
    public void firstNameEmptyIsAccepted()
            throws InterruptedException {

        HomePage homePage = new HomePage(driver);

        homePage.clickMenuLink("Catalog");

       

        ProductPage productPage = new ProductPage(driver);

        productPage.clickProductLink("Grey jacket");

        

        productPage.clickAddToCart();

      

        driver.get("https://sauce-demo.myshopify.com/cart");

      

        CartPage cartPage = new CartPage(driver);

        cartPage.clickCheckout();

       

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        driver.findElement(By.id("email"))
                .sendKeys("test.user@example.com");

        checkoutPage.enterLastName("Talalweh");

        checkoutPage.enterAddress("Test Street");

        checkoutPage.enterCity("Test City");

      

        checkoutPage.enterCardNumber("1");

        checkoutPage.enterExpiryDate("1230");

        checkoutPage.enterSecurityCode("123");

        checkoutPage.enterNameOnCard("Talalweh");

       

        checkoutPage.clickPayNow();

      

        System.out.println(
                "URL after Pay Now: "
                + driver.getCurrentUrl()
        );

        System.out.println(
                "Page text after Pay Now: "
                + checkoutPage.getOrderSummaryText()
        );

        Assert.assertTrue(
                checkoutPage.isOrderConfirmed()
        );
    }


    @Test(priority = 4)
    public void firstNameWithSymbolsIsAccepted()
            throws InterruptedException {

        HomePage homePage = new HomePage(driver);

        homePage.clickMenuLink("Catalog");

      

        ProductPage productPage = new ProductPage(driver);

        productPage.clickProductLink("Grey jacket");

      

        productPage.clickAddToCart();

       

        driver.get("https://sauce-demo.myshopify.com/cart");

       

        CartPage cartPage = new CartPage(driver);

        cartPage.clickCheckout();

        

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        driver.findElement(By.id("email"))
                .sendKeys("test.user@example.com");

        checkoutPage.enterFirstName("@@@@@@");

        checkoutPage.enterLastName("Talalweh");

        checkoutPage.enterAddress("Test Street");

        checkoutPage.enterCity("Test City");

       

        checkoutPage.enterCardNumber("1");

        checkoutPage.enterExpiryDate("1230");

        checkoutPage.enterSecurityCode("123");

        checkoutPage.enterNameOnCard("@@@@@@ Talalweh");

      

        checkoutPage.clickPayNow();

     

        System.out.println(
                "URL after Pay Now: "
                + driver.getCurrentUrl()
        );

        System.out.println(
                "Page text after Pay Now: "
                + checkoutPage.getOrderSummaryText()
        );

        Assert.assertTrue(
                checkoutPage.isOrderConfirmed()
        );
    }


    @Test(priority = 5)
    public void lastNameEmptyShowsError()
            throws InterruptedException {

        HomePage homePage = new HomePage(driver);

        homePage.clickMenuLink("Catalog");

      

        ProductPage productPage = new ProductPage(driver);

        productPage.clickProductLink("Grey jacket");

       

        productPage.clickAddToCart();

       

        driver.get("https://sauce-demo.myshopify.com/cart");

        

        CartPage cartPage = new CartPage(driver);

        cartPage.clickCheckout();

       

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        driver.findElement(By.id("email"))
                .sendKeys("test.user@example.com");

        checkoutPage.enterAddress("Test Street");

        checkoutPage.enterCity("Test City");

      

        checkoutPage.clickPayNow();

      

        Assert.assertTrue(
                checkoutPage.isLastNameErrorShown()
        );
    }


    @Test(priority = 6)
    public void lastNameWithSymbolsIsAccepted()
            throws InterruptedException {

        HomePage homePage = new HomePage(driver);

        homePage.clickMenuLink("Catalog");


        ProductPage productPage = new ProductPage(driver);

        productPage.clickProductLink("Grey jacket");



        productPage.clickAddToCart();

     

        driver.get("https://sauce-demo.myshopify.com/cart");

      

        CartPage cartPage = new CartPage(driver);

        cartPage.clickCheckout();

      

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        driver.findElement(By.id("email"))
                .sendKeys("test.user@example.com");

        checkoutPage.enterLastName("@@@@@@");

        checkoutPage.enterAddress("Test Street");

        checkoutPage.enterCity("Test City");

       
        checkoutPage.enterCardNumber("1");

        checkoutPage.enterExpiryDate("1230");

        checkoutPage.enterSecurityCode("123");

        checkoutPage.enterNameOnCard("@@@@@@");

        

        checkoutPage.clickPayNow();

    

        System.out.println(
                "URL after Pay Now: "
                + driver.getCurrentUrl()
        );

        System.out.println(
                "Page text after Pay Now: "
                + checkoutPage.getOrderSummaryText()
        );

        Assert.assertTrue(
                checkoutPage.isOrderConfirmed()
        );
    }


    @Test(priority = 7)
    public void checkoutHappyPathWithValidTestData()
            throws InterruptedException {

        HomePage homePage = new HomePage(driver);

        homePage.clickMenuLink("Catalog");

        ProductPage productPage = new ProductPage(driver);

        productPage.clickProductLink("Grey jacket");

        productPage.clickAddToCart();

      

        driver.get("https://sauce-demo.myshopify.com/cart");

      

        CartPage cartPage = new CartPage(driver);

        cartPage.clickCheckout();


        CheckoutPage checkoutPage = new CheckoutPage(driver);

        driver.findElement(By.id("email"))
                .sendKeys("test.user@example.com");

        checkoutPage.enterFirstName("Test");

        checkoutPage.enterLastName("User");

        checkoutPage.enterAddress("Test Street");

        checkoutPage.enterCity("Test City");

      

        checkoutPage.enterCardNumber("1");

        checkoutPage.enterExpiryDate("1230");

        checkoutPage.enterSecurityCode("123");

        checkoutPage.enterNameOnCard("Test User");

     

        checkoutPage.clickPayNow();

   

        Assert.assertTrue(
                checkoutPage.isOrderConfirmed()
        );
    }
}