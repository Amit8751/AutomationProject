package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExcelUtil;

public class Base {
	
	protected static String excelPath=null;
	protected static String projectPath=null;
	protected static String sheetName="Sheet1";
	protected static WebDriver driver;
	protected static ExcelUtil excelUtil=null;
	
	public  static void  initialize(){

	       WebDriverManager.chromedriver().setup();
	       driver = new ChromeDriver();
	       driver.get("https://opensource-demo.orangehrmlive.com/");
	       
	       projectPath=System.getProperty("user.dir");
	       excelPath=projectPath+"/Excel/Demo.xlsx";
	       excelUtil= new ExcelUtil(excelPath, sheetName);
	       
	   }
	
	public static void closeBrowser() {
		driver.close();
	}
	
}
