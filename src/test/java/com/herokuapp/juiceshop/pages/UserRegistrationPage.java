package com.herokuapp.juiceshop.pages;

import com.herokuapp.juiceshop.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UserRegistrationPage extends BaseTest {

    @FindBy(id = "emailControl")
    WebElement emailEl;

    @FindBy(id = "passwordControl")
    WebElement passwordEl;

    @FindBy(id = "repeatPasswordControl")
    WebElement repeatPasswordEl;

    @FindBy(name = "securityQuestion")
    WebElement securityQuestionEl;

    @FindBy(className = "mat-option-text")
    List<WebElement> securityQuestionOptionsEl;

    @FindBy(id = "securityAnswerControl")
    WebElement securityAnswerEl;

    @FindBy(id = "registerButton")
    WebElement registerBtn;

    public UserRegistrationPage() {
        PageFactory.initElements(driver, this);
    }

    public UserRegistrationPage fillEmail(String email) {
        emailEl.isDisplayed();
        emailEl.sendKeys(email);
        return this;
    }

    public UserRegistrationPage fillPassword(String password) {
        passwordEl.isDisplayed();
        passwordEl.sendKeys(password);
        return this;
    }

    public UserRegistrationPage fillRepeatPassword(String password) {
        repeatPasswordEl.isDisplayed();
        repeatPasswordEl.sendKeys(password);
        return this;
    }

    public UserRegistrationPage selectSecurityQuestion(String securityQuestion) {
        securityQuestionEl.isDisplayed();
        securityQuestionEl.click();

        for (WebElement opt : securityQuestionOptionsEl) {
            if (opt.getText().trim().equals(securityQuestion.trim())) {
                opt.click();
                break;
            }
        }

        return this;
    }

    public UserRegistrationPage fillSecurityQuestionAnswer(String answer) {
        securityAnswerEl.isDisplayed();
        securityAnswerEl.sendKeys(answer);
        return this;
    }

    public LoginPage clickRegister() {
        registerBtn.isDisplayed();
        registerBtn.click();
        return new LoginPage();
    }

}
