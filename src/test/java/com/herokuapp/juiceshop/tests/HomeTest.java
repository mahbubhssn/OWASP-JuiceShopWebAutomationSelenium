package com.herokuapp.juiceshop.tests;

import com.herokuapp.juiceshop.base.BaseTest;
import com.herokuapp.juiceshop.pages.DashBoardPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomeTest extends BaseTest {

    DashBoardPage homePage;

    @BeforeMethod
    public void setup() {
        initializeBrowser();
    }

    @Test
    public void checkHomePageShouldSucceed() {
        homePage = new DashBoardPage();
        Assert.assertTrue(homePage.hasHomePageTitle());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
