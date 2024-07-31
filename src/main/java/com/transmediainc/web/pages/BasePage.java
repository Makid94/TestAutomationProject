package com.transmediainc.web.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class BasePage extends AbstractPage {

    public BasePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public WebElement getWebElement(By locator) {
        return driver.findElement(locator);
    }

    @Override
    public List<WebElement> getWebElements(By locator) {
        return driver.findElements(locator);
    }

    @Override
    public void waitForTitle(String pageTitle) {
        wait.until(ExpectedConditions.titleContains(pageTitle));
    }

    @Override
    public void waitForWebElement(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception exception) {
            System.out.println("no element is found");
        }
    }

    public void waitForElementVisibility(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception exception) {
            System.out.println("no element is visible");
        }
    }
    public void waitForNumberOfElementsToBe(By locator, int number) {
        try {
            wait.until(ExpectedConditions.numberOfElementsToBe(locator, number));
        } catch (Exception exception) {
            System.out.println("number of elements is not as expected");
        }
    }


    public void scrollToElement(WebElement webElement) {
        webElement.isDisplayed();
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();", webElement);
    }

    public void pressEnterKey() {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).perform();
    }
}
