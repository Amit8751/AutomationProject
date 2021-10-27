package com.hm.test.pages;

import com.hm.framework.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class EmployeeListPage extends BasePage {


    public EmployeeListPage() {

    }

    @FindBy(how = How.CLASS_NAME , using = "btn btn-primary")
    public WebElement btnCreate;
}
