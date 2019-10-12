/*
(//span[text()='Set status'])[2]
(//button[contains(text(),'Edit profile')])[1]
(//a[@class='UnderlineNav-item mr-0 mr-md-1 mr-lg-3 '])[1]
//a[text()='Your repositories']

.pt-1.ws-normal.user-status-message-wrapper.f6>div>span
.d-none.d-md-block>div>div>button
.UnderlineNav-body>a:nth-child(2)
.dropdown-menu.dropdown-menu-sw.mt-2>*:nth-child(6)

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
    public String bRepositories="(//a[@class='UnderlineNav-item mr-0 mr-md-1 mr-lg-3 '])[1]";
    public String bYourRepositories="//a[text()='Your repositories']";

    //a[text()='Your repositories']




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
        driver.navigate().to("https://github.com/login");

        WebDriverWait customWait = new WebDriverWait(driver,10);
        customWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(inputUserName));


        WebElement InputUserName = driver.findElement(By.id(inputUserName));
        InputUserName.sendKeys(sUserName);

        WebElement InputPassword = driver.findElement(By.id(inputPassword));
        InputPassword.sendKeys(sPassword);

        driver.findElement(By.xpath(buttonCommit)).click();
    }

    private void goToProfile() {
        openUserSetup();
        driver.findElement(By.xpath(bProfile)).click();
    }

    private void openUserSetup() {
        driver.findElement(By.xpath(bAvatar)).click();
    }

    @Test
    public void userName(){
        web>
        goToProfile();
        Assert.assertNotNull(driver.findElement(By.xpath(ghUserName)));
    }

    @Test
    public void setStatus(){
        goToProfile();
        Assert.assertNotNull(driver.findElement(By.xpath(bSetStatus)));
    }

    @Test
    public void editProfile(){
        goToProfile();
        Assert.assertNotNull(driver.findElement(By.xpath(bEditProfile)));
    }

    @Test
    public void repositories(){
        goToProfile();
        Assert.assertNotNull(driver.findElement(By.xpath(bRepositories)));
    }

    @Test
    public void yourRepositories(){
        goToProfile();
        Assert.assertNotNull(driver.findElement(By.xpath(bYourRepositories)));
    }

    @AfterTest
    public void testTeardown(){
        driver.quit();
    }
}