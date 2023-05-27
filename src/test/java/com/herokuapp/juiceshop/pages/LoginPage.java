package com.herokuapp.juiceshop.pages;

import com.herokuapp.juiceshop.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseTest {

    @FindBy(id = "email")
    WebElement emailEl;

    @FindBy(id = "password")
    WebElement passwordEl;

    @FindBy(id = "loginButton")
    WebElement loginBtn;

    @FindBy(xpath = "//a[@routerlink='/register']")
    WebElement newCustomerLink;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public DashBoardPage login(String email, String password) {

        emailEl.clear();
        emailEl.sendKeys(email);

        passwordEl.clear();
        passwordEl.sendKeys(password);

        loginBtn.isDisplayed();
        loginBtn.click();

        return new DashBoardPage();
    }

    public UserRegistrationPage clickNewCustomerBtn() {

        newCustomerLink.isDisplayed();
        newCustomerLink.click();

        return new UserRegistrationPage();
    }
}
