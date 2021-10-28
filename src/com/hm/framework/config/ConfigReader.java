package com.hm.framework.config;

import com.hm.framework.base.BrowserType;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public static  void PopulateSettings() throws IOException {
        ConfigReader reader = new ConfigReader();
        reader.ReadProperty();
    }



    private void ReadProperty() throws IOException {
        //Create Property Object
        Properties p = new Properties();
        //Load the Property file available in same package
        p.load(getClass().getClassLoader().getResourceAsStream("GlobalConfig.properties"));
        //Get AUTConnection String
        Settings.AUT_DB_ConnectionString = p.getProperty("AUT_DB_ConnectionString");
        Settings.DB_ConnectionString = p.getProperty("DB_ConnectionString");
        //Get Reporting String
        Settings.ReportingConnectionString = p.getProperty("ReportingConnectionString");
        //Get LogPath
        Settings.LogPath = p.getProperty("LogPath");
        //Get DriverType
        Settings.DB_Driver = p.getProperty("DB_Driver");
        //GEt ExcelSheetPath
        Settings.ExcelSheetPath = p.getProperty("ExcelSheetPath");
        //Get AUT
        Settings.AUT = p.getProperty("AUT");
        //Browser Type
        Settings.BrowserType = BrowserType.valueOf(p.getProperty("BrowserType"));
        //DB Username
        Settings.AUT_DB_User = p.getProperty("AUT_DB_User");
        Settings.DB_User = p.getProperty("DB_User");
        //DB Password
        Settings.AUT_DB_Password = p.getProperty("AUT_DB_Password");
        Settings.DB_Password = p.getProperty("DB_Password");
    }

    public static String getProperty(String property) throws IOException {

        Properties prop = new Properties();
        FileInputStream fis=new FileInputStream("C:/GithubProject/AutomationProject/src/com/hm/framework/config/GlobalConfig.properties");
        prop.load(fis);

        return  prop.getProperty(property);
    }

}
