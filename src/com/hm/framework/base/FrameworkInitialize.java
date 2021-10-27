package com.hm.framework.base;

import com.paulhammant.ngwebdriver.NgWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class FrameworkInitialize extends Base {


    public void OpenBrowser(BrowserType browserType){

        WebDriver driver;
        switch (browserType)
        {

            case Chrome:
            {
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--incognito");
                chromeOptions.addArguments("--no-sandbox");
               // chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);

               // DriverContext.setDriver(driver);
                break;
            }
            case Firefox:
            {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            }
            case IE:
            {
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();

                break;
            }

            default:
            {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }

            //Set the Driver

        }
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        JavascriptExecutor jsDriver = (JavascriptExecutor)driver;
        NgWebDriver ngDriver = new NgWebDriver(jsDriver);
        ngDriver.waitForAngularRequestsToFinish();
        DriverContext.setDriver(driver);
        DriverContext.Browser = new Browser(driver);

    }



}
