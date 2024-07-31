package com.transmediainc.web.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class BoardDetailsPage extends BasePage {
    public BoardDetailsPage(WebDriver driver) {
        super(driver);
    }

    private final By HOME_BUTTON = By.xpath("//button[@data-cy='home']");
    private final By BOARD_OPTIONS = By.cssSelector("*[data-cy='board-options']");
    private final By DELETE_BOARD = By.cssSelector("*[data-cy='delete-board']");
    private final By LIST_TITLE = By.cssSelector("*[data-cy='add-list-input']");
    private final By ADD_LIST = By.xpath("//*[contains(text(),'Add list')]");
    private final By LISTS = By.cssSelector("*[data-cy='list-placeholder']");
    private final By LIST_OPTIONS = By.cssSelector("*[data-cy='list-options']");
    //private final By DELETE_LIST = By.cssSelector("*[data-cy='delete-list']");
    private final By DELETE_LIST = By.xpath("//*[contains(text(),'Delete list')]");


    public HomePage clickHomeButton() {
        waitForWebElement(HOME_BUTTON);
        getWebElement(HOME_BUTTON).click();
        return getInstance(HomePage.class);
    }

    public BoardDetailsPage fillListTitle(String title) {
        getWebElement(LIST_TITLE).sendKeys(title);
        return this;
    }

    public <T extends BasePage> T clickAddListButton(Class<T> clazz) {
        getWebElement(ADD_LIST).click();
        return getInstance(clazz);
    }

    public HomePage deleteBoard() {
        getWebElement(BOARD_OPTIONS).click();
        getWebElement(DELETE_BOARD).click();
        return getInstance(HomePage.class);
    }

    public BoardDetailsPage createTwoLists(String firstListTitle, String secondListTitle) {
        fillListTitle(firstListTitle);
        clickAddListButton(BoardDetailsPage.class);
        fillListTitle(secondListTitle);
        return clickAddListButton(BoardDetailsPage.class);
    }

    public boolean hasTwoLists() {
        waitForElementVisibility(LISTS);
        return getWebElements(LISTS).size() == 2;
    }

    public boolean hasOneList() {
        return getWebElements(LISTS).size() == 1;
    }

    public BoardDetailsPage deleteFirstList()  {
        List<WebElement> lists = getWebElements(LIST_OPTIONS);
        lists
                .getFirst()
                .click();
        getWebElement(DELETE_LIST).click();
        waitForNumberOfElementsToBe(LISTS, 1);
        return this;
    }
}


