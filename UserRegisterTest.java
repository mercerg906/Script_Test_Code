package newpackage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class UserRegisterTest
{
    static int usrsToCreate = 2; // how many users to crate
    static int usrNum = 6000;  //set for unique usr number
    static String usrPass = "123456"; // password to use
    
    public static int delay = 1000; //delay in milliseconds
    
    public static void main (String[] args) throws InterruptedException{
        
        UserRegisterTest program = new UserRegisterTest();
        
        WebDriver driver;
        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver\\geckodriver.exe");
        driver = new FirefoxDriver();
                
        driver.get("https://gabriel-nbon.c9users.io/");
        
        program.createUsers(driver);
        
    }
    
    public void createUsers(WebDriver driver) throws InterruptedException{
        
        String usrName;
        String usrEmail;
        
        for(int i = 0; i < usrsToCreate; i++){
            
            //setting user name and email to register
            usrName = "auto Test Name " + usrNum;
            usrEmail = "autoTestEmail" + usrNum + "@aim.com";
            
            //find and click the register button
            Thread.sleep(delay);
            driver.findElement(By.linkText("Register")).click();
            
            //filling out the register form with usrName, usrEmail, and usrPassword
            Thread.sleep(delay);
            driver.findElement(By.id("name")).sendKeys(usrName);
            driver.findElement(By.id("email")).sendKeys(usrEmail);
            driver.findElement(By.id("password")).sendKeys(usrPass);
            driver.findElement(By.id("password-confirm")).sendKeys(usrPass);
            
            //submitting register form
            Thread.sleep(delay);
            driver.findElement(By.id("regBtn")).submit();
            
            //logout
            Thread.sleep(delay);
            driver.findElement(By.id("dynLink")).click();
            Thread.sleep(delay);
            driver.findElement(By.id("logOutBtn")).click();
            
            usrNum++; //used for next usrName and email to be crated
        }
    }
}
