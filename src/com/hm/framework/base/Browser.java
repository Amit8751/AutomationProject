package com.hm.framework.base;

import com.paulhammant.ngwebdriver.NgWebDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class Browser {

    private WebDriver _driver;

    public BrowserType Type;

    public Browser(WebDriver driver) {

        _driver = driver;
    }

    public void GoToUrl(String url){

        _driver.get(url);

    }
    public void MaximizeWindow(){

        _driver.manage().window().maximize();
    }

    public void closeBrowser(){

        _driver.close();
    }

    public void acceptAlert(){

       Alert alert = _driver.switchTo().alert();
       alert.accept();
    }

    public String getAlertText(){

        Alert alert = _driver.switchTo().alert();
       return alert.getText();
    }

    public  NgWebDriver ngWebDriver(){
        JavascriptExecutor js = (JavascriptExecutor) _driver;
        return new NgWebDriver(js);
    }



}
