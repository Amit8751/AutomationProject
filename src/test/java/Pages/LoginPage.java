package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import Base.BasePage;

public class LoginPage extends BasePage{
	
	  public LoginPage(WebDriver driver) {
	        super(driver);
	    }

	    @FindBy(how = How.ID , using = "txtUsername")
	    public WebElement txtUserName;

	    @FindBy(how = How.ID , using = "txtPassword")
	    public WebElement txtPassword;

	    @FindBy(how = How.ID , using = "btnLogin")
	    public WebElement btnLogin;
	    
	    
	    public void Login(String userName, String password){

	        txtUserName.sendKeys(userName);
	        txtPassword.sendKeys(password);
	        btnLogin.submit();
	    }

	    

}
