package com.herokuapp.juiceshop.pages;

import com.herokuapp.juiceshop.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashBoardPage extends BaseTest {

    @FindBy(id = "navbarAccount")
    WebElement accountBtn;

    @FindBy(id = "navbarLoginButton")
    WebElement loginBtn;

    public DashBoardPage() {
        PageFactory.initElements(driver, this);
    }

    public boolean hasHomePageTitle() {
        return driver.getTitle().trim().contains(properties.getProperty("homePageTitle"));
    }

    public DashBoardPage clickAccountBtn() {
        accountBtn.isDisplayed();
        accountBtn.click();
        return this;
    }

    public DashBoardPage clickLoginBtn() {
        loginBtn.isDisplayed();
        loginBtn.click();
        return this;
    }
}
