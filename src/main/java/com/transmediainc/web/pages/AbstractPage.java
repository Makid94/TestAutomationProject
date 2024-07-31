package com.transmediainc.web.pages;

import com.transmediainc.web.utils.WaitConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.util.List;

public abstract class AbstractPage {
    protected WebDriver driver;
    WebDriverWait wait;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(WaitConfig.WAIT_TIME));
    }

    public abstract String getTitle();

    public abstract WebElement getWebElement(By locator);

    public abstract List<WebElement> getWebElements(By locator);

    public abstract void waitForTitle(String pageTitle);

    public abstract void waitForWebElement(By locator);

    public <T extends BasePage> T getInstance(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
