package com.transmediainc.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    private final By CREATE_BOARD = By.xpath("//*[contains(text(),'Create new board')]");
    private final By ADD_BOARD_TITLE = By.cssSelector("input[data-cy='new-board-input']");
    private final By BOARD = By.xpath("//*[contains(@data-cy, 'board-item')][last()]");
    private final By SUCCESS_ALERT = By.xpath("//*[contains(text(),'Board was deleted')]");

    public HomePage clickNewBoard() {
        scrollToElement(getWebElement(CREATE_BOARD));
        waitForWebElement(CREATE_BOARD);
        getWebElement(CREATE_BOARD).click();
        return this;
    }

    public HomePage addBoardTitle(String boardTitle) {
        waitForWebElement(ADD_BOARD_TITLE);
        getWebElement(ADD_BOARD_TITLE).sendKeys(boardTitle);
        return this;
    }

    public BoardDetailsPage creatNewBoard(String boardTitle){
        clickNewBoard().addBoardTitle(boardTitle);
        pressEnterKey();
        return getInstance(BoardDetailsPage.class);
    }

    public String getBoardTitle() {
        return getWebElement(BOARD).getText();
    }
    public boolean hasBoardDeletionSuccessAlert() {
        waitForElementVisibility(SUCCESS_ALERT);
        return !getWebElements(SUCCESS_ALERT).isEmpty();
    }
}
