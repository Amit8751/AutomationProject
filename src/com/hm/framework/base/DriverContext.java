package com.hm.framework.base;

import com.paulhammant.ngwebdriver.NgWebDriver;
import org.openqa.selenium.WebDriver;

public class DriverContext {

    public static WebDriver Driver;
    public static NgWebDriver ngWebDriver;

    public static Browser Browser;

    public static void setDriver(WebDriver driver) {
        Driver = driver;
    }

    public static void setNgWebDriver(NgWebDriver ngDriver) {

        ngWebDriver = ngDriver;
    }
}
