
package localbrowsers;

import com.google.gson.annotations.Until;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Test(groups = {"mac", "windows"})
public class Chrome {
    private WebDriver driver;

    public String gTitleLocator=".g-title";
    public String lButton = "//a[@href='https://seleniumaboveandbeyond.com' and contains(@class,'button')]";
    public String inputUserName = "login_field";
    public String sUserName = "lineth.mendieta.n@gmail.com";
    public String inputPassword = "password";
    public String sPassword = "Linemn2503";
    public String buttonCommit = "//input[@name='commit']";
    public String ghUserName="(//span[contains(text(),'miguelcoca')])[8]";

    @BeforeTest
    public void chromeSetup(){
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
    }
    @Test
    public void codigo() throws InterruptedException {
        loginAction();
        WebElement Proyecto = driver.findElement(By.xpath("(//span[contains(.,'SelSimpleDemo')])[2]"));
        Proyecto.click();
        WebElement Browser = driver.findElement(By.xpath("//span[@class='simplified-path']"));
        Browser.click();
        Thread.sleep (5000);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement fileJava = driver.findElement(By.xpath("//a[@title='Chrome.java']"));
        fileJava.click();
        WebElement editFile = driver.findElement(By.xpath("//button[contains(@aria-label,'Edit this file')]"));
        editFile.click();
        Thread.sleep (10000);
    }


    @Test
    public void login(){
        loginAction();
        Assert.assertNotNull(driver.findElement(By.xpath(ghUserName)));
    }

    private void loginAction() {
        driver.navigate().to("https://github.com/login");


        WebDriverWait customWait = new WebDriverWait(driver,60);
        customWait.until(ExpectedConditions.
                visibilityOfElementLocated(By.id(inputUserName)));

        WebElement InputUserName = driver.findElement(By.id(inputUserName));
        InputUserName.sendKeys(sUserName);

        WebElement InputPassword = driver.findElement(By.id(inputPassword));
        InputPassword.sendKeys(sPassword);

        driver.findElement(By.xpath(buttonCommit)).click();
    }


    @Test
    public void test(){
        driver.get("http://lazycoder.io/feedback");
        Assert.assertEquals(driver.getTitle(), "Feedback | Lazy Coder IO");
    }

    @Test
    public void testGTitle(){
        driver.get("http://lazycoder.io");
        WebElement gTitle = driver.findElement(By.cssSelector(gTitleLocator));
        Assert.assertTrue(gTitle.getText().contains("FROM THE BLOG"));
    }

    @Test
    public void clickLearnButotn(){
        driver.get("http://lazycoder.io");

        List<WebElement> buttons = driver.findElements(By.xpath(lButton));

        for(WebElement button:buttons){
            if(button.getText().contains("LEARN")){
                //we should interact with the element that matches our criteria.
                //button.click();
            }

            button.click();
            break;
        }
    }

    @AfterTest
    public void testTeardown(){
        driver.quit();
    }
}
