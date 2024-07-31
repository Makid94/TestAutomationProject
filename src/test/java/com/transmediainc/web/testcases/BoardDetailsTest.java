package com.transmediainc.web.testcases;

import com.thedeanda.lorem.LoremIpsum;
import com.transmediainc.web.pages.BoardDetailsPage;
import com.transmediainc.web.pages.HomePage;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BoardDetailsTest extends BaseTest {

    public static class LoginTest extends BaseTest {
        private Logger log;

        @Test(groups = {"smoke"})
        public void createNewBoard() {
            String newBoardName = LoremIpsum.getInstance().getTitle(1);
            HomePage homePage = abstractPage.getInstance(HomePage.class)
                    .creatNewBoard(newBoardName)
                    .clickHomeButton();
            String actualBoardName = homePage.getBoardTitle();
            System.out.println(actualBoardName);
            Assert.assertEquals(actualBoardName, newBoardName, "Board name does not match!");
        }

    }

    @Test(groups = {"smoke"})
    public void createTwoLists() {
        String newBoardName = LoremIpsum.getInstance().getTitle(1);
        String firstList = LoremIpsum.getInstance().getTitle(1);
        String secondList = LoremIpsum.getInstance().getTitle(1);

        BoardDetailsPage boardDetailsPage = abstractPage.getInstance(HomePage.class)
                .creatNewBoard(newBoardName)
                .createTwoLists(firstList, secondList);
        Assert.assertTrue(boardDetailsPage.hasTwoLists());
    }

    @Test(groups = {"smoke"})
    public void deleteList() throws InterruptedException {
        String newBoardName = LoremIpsum.getInstance().getTitle(1);
        String firstList = LoremIpsum.getInstance().getTitle(1);
        String secondList = LoremIpsum.getInstance().getTitle(1);

        BoardDetailsPage boardDetailsPage = abstractPage.getInstance(HomePage.class)
                .creatNewBoard(newBoardName)
                .createTwoLists(firstList, secondList);
        Assert.assertTrue(boardDetailsPage.hasTwoLists());
        boardDetailsPage.deleteFirstList();
        Assert.assertTrue(boardDetailsPage.hasOneList());
        System.out.println("one list deleted");
    }
}