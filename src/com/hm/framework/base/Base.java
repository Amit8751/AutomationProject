package com.hm.framework.base;

import org.openqa.selenium.support.PageFactory;

public class Base {

    public static BasePage CurrentPage;


    public <TPage extends BasePage> TPage GetInstance(Class<TPage> page) {

        Object obj = PageFactory.initElements(DriverContext.Driver , page);
        return page.cast(obj);
    }

//    public <TPage extends BasePage> TPage GetNgInstance(Class<TPage> page) {
//
//        Object obj = PageFactory.initElements(DriverContext.ngWebDriver , page);
//        return page.cast(obj);
//    }




}
