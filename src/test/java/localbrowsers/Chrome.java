/*
(//span[text()='Set status'])[2]
(//button[contains(text(),'Edit profile')])[1]

*/
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
    public String sUserName = "agustin.colque@gmail.com";
    public String inputPassword = "password";
    public String sPassword = "Coflesto7";
    public String buttonCommit = "//input[@name='commit']";
    public String ghUserName="(//span[contains(text(),'Maloto7')])[1]";
    public String bProfile="//a[contains(text(),'Your profile')]";
    public String bAvatar="(//*[@class='avatar'])[2]";
    public String bSetStatus="(//span[text()='Set status'])[2]";
    public String bEditProfile="(//button[contains(text(),'Edit profile')])[1]";




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

    @AfterTest
    public void testTeardown(){
        driver.quit();
    }
}