package com.hm.test.pages;

import com.hm.framework.base.Base;
import com.hm.framework.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    public LoginPage() {

    }

    @FindBy(how = How.CSS , using = "input[name='txtUsername']")
    public WebElement txtUserName;

    @FindBy(how = How.CSS , using = "input[name='txtPassword']")
    public WebElement txtPassword;

    @FindBy(how = How.CSS , using = "input[type='submit']")
    public WebElement btnLogin;




    public BasePage Login(String userName, String password){

        txtUserName.sendKeys(userName);
        txtPassword.sendKeys(password);
        btnLogin.submit();

        return GetInstance(HomePage.class);
    }


}
