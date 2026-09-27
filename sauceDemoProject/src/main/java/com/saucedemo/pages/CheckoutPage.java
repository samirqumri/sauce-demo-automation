
package com.saucedemo.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

    private WebDriver driver;

    private By pageBody = By.tagName("body");

    private By orderSummaryItems =
            By.cssSelector(".order-summary__item, .product");

    private By removeOrder =
            By.xpath("//a[text()='x']");

    private By payNowButton =
            By.id("checkout-pay-button");

    private By emailErrorMessage =
            By.id("error-for-email");

    private By firstNameInput =
            By.name("firstName");

    private By lastNameInput =
            By.name("lastName");

    private By addressInput =
            By.name("address1");

    private By cityInput =
            By.name("city");

    private By numberFrame =
            By.cssSelector("iframe[id^='card-fields-number-']");

    private By expiryFrame =
            By.cssSelector("iframe[id^='card-fields-expiry-']");

    private By securityCodeFrame =
            By.cssSelector("iframe[id^='card-fields-verification_value-']");

    private By nameOnCardFrame =
            By.cssSelector("iframe[id^='card-fields-name-']");

    private By orderConfirmedText =
            By.xpath("//*[contains(text(),'Thank you')]");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getOrderSummaryText() {
        return driver.findElement(pageBody).getText();
    }

    public int getOrderItemsCount() {
        return driver.findElements(orderSummaryItems).size();
    }

    public void ToRemoveOrder() {
        driver.findElement(removeOrder).click();
    }

    public void clickPayNow() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.elementToBeClickable(payNowButton)
        ).click();
    }

    public String getEmailErrorText() {
        return driver.findElement(emailErrorMessage).getText();
    }

    public void enterFirstName(String value) {
        driver.findElement(firstNameInput).sendKeys(value);
    }

    public void enterLastName(String value) {
        driver.findElement(lastNameInput).sendKeys(value);
    }

    public boolean isLastNameErrorShown() {
        return driver.findElement(pageBody)
                .getText()
                .contains("Enter a last name");
    }

    public void enterAddress(String value) {
        driver.findElement(addressInput).sendKeys(value);
    }

    public void enterCity(String value) {
        driver.findElement(cityInput).sendKeys(value);
    }

    public void enterCardNumber(String value) {

        driver.switchTo().frame(
                driver.findElement(numberFrame)
        );

        driver.findElement(By.id("number"))
                .sendKeys(value);

        driver.switchTo().defaultContent();
    }

    public void enterExpiryDate(String value) {

        driver.switchTo().frame(
                driver.findElement(expiryFrame)
        );

        driver.findElement(By.id("expiry"))
                .sendKeys(value.substring(0, 2));

        driver.findElement(By.id("expiry"))
                .sendKeys(value.substring(2));

        driver.switchTo().defaultContent();
    }

    public void enterSecurityCode(String value) {

        driver.switchTo().frame(
                driver.findElement(securityCodeFrame)
        );

        driver.findElement(By.id("verification_value"))
                .sendKeys(value);

        driver.switchTo().defaultContent();
    }

    public void enterNameOnCard(String value) {

        driver.switchTo().frame(
                driver.findElement(nameOnCardFrame)
        );

        driver.findElement(By.id("name"))
                .clear();

        driver.findElement(By.id("name"))
                .sendKeys(value);

        driver.switchTo().defaultContent();
    }

    public boolean isOrderConfirmed() {
        return driver.findElements(orderConfirmedText).size() > 0;
    }
}

