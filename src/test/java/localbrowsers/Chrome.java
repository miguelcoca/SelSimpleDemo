package localbrowsers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test(groups = {"mac", "windows"})
public class Chrome {
    private WebDriver driver;
    public String gTitleLocator=".g-title";
    public String lButton = "//a[@href='https://seleniumaboveandbeyond.com' and contains(@class,'button')]";
    public String inputUserName = "login_field";
    public String sUserName = "daniel-sj77@hotmail.com";
    public String inputPassword = "password";
    public String sPassword = "miPasswordSecreto";
    public String buttonCommit = "//input[@name='commit']";
    public String ghUserName="(//span[contains(text(),'danieldas')])[8]";

/*
-Tab Pull requests
   Xpath:    //a[(contains(@class,'js')) and (@href='/miguelcoca/SelSimpleDemo/pulls')]          
   Css:      a[href='/miguelcoca/SelSimpleDemo/pulls']
-Boton Browse Files
   Xpath:   //a[(text()='Browse files')]                    
   Css:     a[title='Browse the repository at this point in the history']
-1 parent ....
   Xpath:   //div[(@class='flex-auto no-wrap text-right')]              
   Css:     div[class='flex-auto no-wrap text-right']
-Boton Split
   Xpath:   //a[contains(text(),'Split')]           
   Css:     a[href='https://github.com/miguelcoca/SelSimpleDemo/commit/4a8f0ff5e879d687b24c9b39f17ffdf6358c6952?diff=split']
-Expand all
   Xpath:   //a[contains(@title,'Expand All')]      
   Css:     a[title='Expand All']  

-Boton Edit profile
   Xpath:   (//button[(text()='Edit profile') and (@name='button')])[1]             
   Css:     div.d-none div.js-profile-editable-area div.hide-sm button[name='button']  
-Set status
   Xpath:   (//span[(text()='Set status')])[2]                      
   Css:     div.pt-1.ws-normal div.d-inline-block.text-gray-dark span.text-gray.ml-2
-Tab repositories
   Xpath:   //a[(contains(@class,'UnderlineNav')) and (@href='/danieldas?tab=repositories')]                
   Css:     a.UnderlineNav-item[href='/danieldas?tab=repositories']
-Menu Your repositories
   Xpath:   //a[(contains(@class,'drop')) and (@href='/danieldas?tab=repositories')]            
   Css:     a.dropdown-item[href='/danieldas?tab=repositories']

*/

    @BeforeTest
    public void chromeSetup(){
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
    }
    
    @Test
    public void login(){
        loginAction();
        Assert.assertNotNull(driver.findElement(By.xpath(ghUserName)));
    }

    private void loginAction() {
        driver.navigate().to("https://github.com/login");

        WebElement InputUserName = driver.findElement(By.id(inputUserName));
        InputUserName.sendKeys(sUserName);

        WebElement InputPassword = driver.findElement(By.id(inputPassword));
        InputPassword.sendKeys(sPassword);

        driver.findElement(By.xpath(buttonCommit)).click();
    }

    @Test
    public void test(){
        driver.get("https://www.youtube.com/");
        Assert.assertEquals(driver.getTitle(), "YouTube");
    }
    @Test
    public void test2(){
        driver.get("https://www.youtube.com/?hl=es-419");
        Assert.assertEquals(driver.getTitle(), "YouTube");
    }
    @AfterTest
    public void testTeardown(){
        driver.quit();
    }
}
