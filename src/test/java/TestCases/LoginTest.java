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
import utilities.ExcelUtil;

public class LoginTest extends Base{
  
	private static Logger logger = LogManager.getLogger();
   

   @BeforeTest
   public  void  setUp(){
	   initialize();
	   logger.info("----Setup completed successfully----");
   }


    @Test
    public void Login(){
    	
        LoginPage page = new LoginPage(driver);
        page.Login( excelUtil.getCellDataString(1, 0), excelUtil.getCellDataString(1, 1));
        logger.info("----Test case completed successfully----");
    }
    
    @AfterTest
    public void tearDown() {
    	closeBrowser();
    	logger.info("----Browser closed successfully----");
    }

}
