package TestCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Base.Base;
import Pages.LoginPage;
import utilities.DatabaseUtil;
import utilities.ExcelUtil;
import utilities.JsonDBComparison;
import utilities.JsonFileUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class LoginTest extends Base{
  
	private static Logger logger = LogManager.getLogger();
    private static List<Map<String,String>> testData;

   @BeforeTest
   public  void  setUp(){
	   //initialize();
	   logger.info("----Setup completed successfully----");
   }


    @Test
    public void Login() throws SQLException {
    	
        //LoginPage page = new LoginPage(driver);
        //DatabaseUtil databaseUtil=new DatabaseUtil();
        //page.Login( excelUtil.getCellDataString(1, 0), excelUtil.getCellDataString(1, 1));
        //page.Login(ExcelUtil.getTestDataInMap().get(0).get("Username"),ExcelUtil.getTestDataInMap().get(0).get("Password"));

        System.out.println(JsonFileUtil.getJsonDataInMap());
        System.out.println(DatabaseUtil.getSqlResultInMap());

        JsonDBComparison.jsonDataToDBDataComparison(JsonFileUtil.getJsonDataInMap(),DatabaseUtil.getSqlResultInMap());

        //System.out.println(JsonFileUtil.getJsonDataInMap().equals(DatabaseUtil.getSqlResultInMap()));
        logger.info("----Test case completed successfully----");
    }
    
    @AfterTest
    public void tearDown() {
    	//closeBrowser();
    	logger.info("----Browser closed successfully----");
    }

}
