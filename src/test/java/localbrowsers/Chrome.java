/*
(//span[text()='Set status'])[2]
(//button[contains(text(),'Edit profile')])[1]
(//a[@class='UnderlineNav-item mr-0 mr-md-1 mr-lg-3 '])[1]
//a[text()='Your repositories']
.pt-1.ws-normal.user-status-message-wrapper.f6>div>span
.d-none.d-md-block>div>div>button
.UnderlineNav-body>a:nth-child(2)
.dropdown-menu.dropdown-menu-sw.mt-2>*:nth-child(6)
espira test
*/
package localbrowsers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
    public String sUserName = "agustin.colque@gmail.com";
    public String inputPassword = "password";
    public String sPassword = "Coflesto7";
    public String buttonCommit = "//input[@name='commit']";
    public String ghUserName="(//span[contains(text(),'Maloto7')])[1]";
    public String bProfile="//a[contains(text(),'Your profile')]";
    public String bAvatar="(//*[@class='avatar'])[2]";
    public String bSetStatus="(//span[text()='Set status'])[2]";
    public String bEditProfile="(//button[contains(text(),'Edit profile')])[1]";
    public String bRepositories="(//span[@class='css-truncate css-truncate-target'])[2]";
    public String bYourRepositories="//a[text()='Your repositories']";

    public String bSingIn="//a[@class='HeaderMenu-link no-underline mr-3']";
    public String lCode="//span[@class='simplified-path']";
    public String chromeFile="(//a[@class='js-navigation-open'])[2]";



    @BeforeTest
    public void chromeSetup(){
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
        driver.manage().window().maximize();
        loginAction();
    }

    private void loginAction() {
        driver.navigate().to("https://github.com");
        driver.findElement(By.xpath(bSingIn)).click();
//        WebDriverWait customWait = new WebDriverWait(driver,10);
//        customWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(inputUserName)));
        WebElement InputUserName = driver.findElement(By.id(inputUserName));
        InputUserName.sendKeys(sUserName);
        WebElement InputPassword = driver.findElement(By.id(inputPassword));
        InputPassword.sendKeys(sPassword);
        driver.findElement(By.xpath(buttonCommit)).click();
    }

    private void goToRepositories() {
        driver.findElement(By.xpath(bRepositories)).click();
        driver.findElement(By.xpath(lCode)).click();
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException ie){
        }
        driver.findElement(By.xpath(chromeFile)).click();
    }

    @Test
    public void code(){
        goToRepositories();
        Assert.assertNotNull(driver.findElement(By.xpath(bYourRepositories)));
    }

    @AfterTest
    public void testTeardown()
    {
//        driver.quit();
    }
}