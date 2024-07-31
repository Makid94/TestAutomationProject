package com.transmediainc.web.testcases;
import com.transmediainc.web.pages.AbstractPage;
import com.transmediainc.web.pages.BasePage;
import com.transmediainc.web.utils.WaitConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Objects;
import java.util.Properties;

public class BaseTest {
    WebDriver driver;
    AbstractPage abstractPage;
    private final Properties properties;

    public Logger log;

    public BaseTest() {
        String path = System.getProperty("user.dir") + ("/src/main/resources/credential.properties");
        try {
            properties = new Properties();
            FileInputStream inputStream = new FileInputStream(path);
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeMethod
    @Parameters("BrowserType")
    public void setupBrowser(String BrowserType) {
        String browserName = BrowserType;
        log = LogManager.getLogger(browserName);
        if (Objects.equals(BrowserType, "edge")) {
            driver = new EdgeDriver();
        } else if (Objects.equals(browserName, "chrome")) {
            driver = new ChromeDriver();
        } else if (Objects.equals(browserName, "firefox")) {
            driver = new FirefoxDriver();
        } else if (Objects.equals(browserName, "headless")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("-headless");
            driver = new ChromeDriver(options);
        } else {
            log.info("Please provide right Browser Name");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(WaitConfig.PAGE_LOAD_TIME));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WaitConfig.IMPLICIT_WAIT));
        driver.get(properties.getProperty("baseUrl"));
        abstractPage = new BasePage(driver);
    }

    @AfterMethod
    public void closeBrowser() {
        log.info("browser is closed");
        driver.quit();
    }

}
