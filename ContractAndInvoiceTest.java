package newpackage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ContractAndInvoiceTest
{
    int conCount = 0; //used to keep printed count of contracts in description
    int invCount = 0;
    
    int consToCreate = 1; // number o contracts to create
    int invToCreate = 1; // number of invoices to create
    int itemsToCreate = 1; //number of items in an invoice to create
    
    static String usrEmail = "grmercer906@aim.com";
    
    public static int delay = 1000; //delay in milliseconds
    
    public static void main (String[] args) throws InterruptedException{
        
        ContractAndInvoiceTest program = new ContractAndInvoiceTest();
        
        /*
         * creating "driver", which represents the page flow/focus. 
         * this driver is used to invoke web driver methods and to 
         * interact with the page elements (called WebElements)
         */
        WebDriver driver;
        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver\\geckodriver.exe");
        driver = new FirefoxDriver();
                
        driver.get("https://gabriel-nbon.c9users.io/");
 
        WebElement search = driver.findElement(By.linkText("Login")); //logging in with set user
        search.click();
        
        Thread.sleep(2000);

        /*
         * filling out login form with information for indicated user
         */
        WebElement login = driver.findElement(By.id("email"));
        login.sendKeys(usrEmail);
        
        WebElement passWord = driver.findElement(By.id("password"));
        passWord.sendKeys("123456");
        
        WebElement loginBtn = driver.findElement(By.id("loginBtn"));
        loginBtn.click();
        /*
         * Finished logging in for indicated user
         */
        
        //putting focus on Contract page
        Thread.sleep(delay);   
        WebElement nav = driver.findElement(By.linkText("Contract"));  
        nav.click();
        
        program.createContract(driver);
        program.createInvoices(driver);
        program.addInvoiceItems(driver);
        
    }
    
    public void createInvoices(WebDriver driver) throws InterruptedException{
        
        WebElement nav;
        
        Thread.sleep(delay);
        nav = driver.findElement(By.linkText("Invoices"));//clicking invoice button
        nav.click();
        
        for(int i = 0; i < invToCreate; i++){
           
            Thread.sleep(delay);
            nav = driver.findElement(By.id("conBtn")); //clicking add invoice button to get to invoices
            nav.click();
            
            Thread.sleep(delay);
            nav = driver.findElement(By.id("due")); //setting due date of invoice
            nav.sendKeys("2016/10/20");
            
            nav = driver.findElement(By.id("subBtn")); //submitting invoice
            nav.click();
        }
        
        //createContract(driver);
        
    }
    
    public void createContract(WebDriver driver) throws InterruptedException{
        
        String conDesc = "Automated test contract ";
        for(int i = 0; i < consToCreate; i ++){
            
            conCount++;
            Thread.sleep(delay);
            WebElement nav = driver.findElement(By.linkText("Contract"));
            nav.click();
            
            Thread.sleep(delay);
            nav = driver.findElement(By.id("conAddBtn"));
            nav.click();
            
            Thread.sleep(delay);
            driver.findElement(By.id("contract-name")).sendKeys(conDesc + conCount);
            driver.findElement(By.id("contract-tenant")).sendKeys(usrEmail);
            driver.findElement(By.id("contract-description")).sendKeys("automated test des.");
            
            Thread.sleep(100);
            driver.findElement(By.id("conSubBtn")).submit();
        }    
    }
    
    public void addInvoiceItems(WebDriver driver) throws InterruptedException{
            
        int itemCount = 0;
        
            Thread.sleep(delay);
            driver.findElement(By.linkText("View")).click();
            
            for(int i = 0; i < itemsToCreate; i++){
                
                itemCount++;
                Thread.sleep(delay);
                driver.findElement(By.id("itemAddBtn")).click(); //clicking add item button
                
                Thread.sleep(delay);
                driver.findElement(By.id("item-name")).sendKeys("test item " + itemCount);
                
                driver.findElement(By.id("itemSubBtn")).click();
            }
            
            Thread.sleep(delay + 2000);
            driver.findElement(By.linkText("Contract")).click();
        }
    
}
