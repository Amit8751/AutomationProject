package Selnium.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		Wait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
		String productName = "ZARA COAT 3";
        driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.cssSelector("#userEmail")).sendKeys("anshika@gmail.com");
        driver.findElement(By.cssSelector("#userPassword")).sendKeys("Iamking@000");
        driver.findElement(By.cssSelector("#login")).click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        
        WebElement prod=products.stream().filter(product->
        product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
        
        List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
        
        Assert.assertTrue(match);
        
        driver.findElement(By.xpath("//button[text()='Checkout']")).click();
        
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "india").build().perform();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        
        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
        
        driver.findElement(By.cssSelector(".btnn.action__submit")).click();
        
        String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
        
        confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER.");
        
        driver.close();
	}

}
