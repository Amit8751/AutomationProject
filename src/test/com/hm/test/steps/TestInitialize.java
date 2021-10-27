package com.hm.test.steps;

import com.hm.framework.base.DriverContext;
import com.hm.framework.base.FrameworkInitialize;
import com.hm.framework.config.ConfigReader;
import com.hm.framework.config.Settings;
import com.hm.framework.utilities.DatabaseUtil;
import com.hm.framework.utilities.ExcelUtil;
import com.hm.framework.utilities.Log;
import com.hm.framework.utilities.LogUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TestInitialize extends FrameworkInitialize {

    private static Logger Log = LogManager.getLogger(Log.class.getName());

    @Before
    public  void  Initialize()  throws IOException, SQLException {
        Log.info("Log has been Started..");
        ConfigReader.PopulateSettings();
//        DatabaseUtil db = DatabaseUtil.getInstance();
//        DatabaseUtil.executeQueryWithOutParam("select * from employee" , db.getConnection());
//      //  List<Map<String, String>> data = DatabaseUtil.getSqlResultInMap("select * from employee", db.getConnection());
//      //  Log.info("The Query has been Run Successfully :" + data);
//        DatabaseUtil.closeConnection();

        Log.info("Log has been Initilaize..");

        OpenBrowser(Settings.BrowserType);
        DriverContext.Browser.GoToUrl(Settings.AUT);
        Log.info("Hello , The Browser has been Started " +Settings.BrowserType);
        Log.info("Hello , The Application Navigation done " +Settings.AUT);


        try{
            ExcelUtil excelUtil = new ExcelUtil("TestData/SampleTestData.xlsx");
            Log.info("Hello , The Test Data has been read from excel " +ExcelUtil.ReadCell("UserName",1));
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }


    @After
    public void closeBrowser(){

        DriverContext.Browser.closeBrowser();
        Log.info("Hello , The Browser has been Closed Successfully " );
    }


}

