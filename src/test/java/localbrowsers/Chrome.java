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
import org.openqa.selenium.JavascriptExecutor;
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

import java.util.List;


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
    public String bRepositories="(//span[@class='css-truncate css-truncate-target'])[2]";

    public String bSingIn="//a[@class='HeaderMenu-link no-underline mr-3']";
    public String lCode="//span[@class='simplified-path']";
    public String chromeFile="(//a[@class = 'js-navigation-open'])[2]";
    public String codeLines="(//div[@class = 'text-mono f6 flex-auto pr-3 flex-order-2 flex-md-order-1 mt-2 mt-md-0'])";
    public String bEdit="//button[@class = 'btn-octicon tooltipped tooltipped-nw']";
    public String tCodeLines=".CodeMirror-code>div:last-child>div>div";
    public String scrollBar="//div[@class = 'CodeMirror-sizer']";


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
    private void waitPage() {
        try{
            Thread.sleep(2000);
        }
        catch(InterruptedException ie){
        }
    }
    private String totalLines() {
        String lines = driver.findElement(By.xpath(codeLines)).getText();
        String[] totalLines = lines.split("\\s+");
        return(totalLines[2]);
    }

    private String[] codeLines() {
        driver.findElement(By.xpath(bRepositories)).click();
        driver.findElement(By.xpath(lCode)).click();
        waitPage();
        driver.findElement(By.xpath(chromeFile)).click();
        waitPage();
        String linesTotal = totalLines();
        driver.findElement(By.xpath(bEdit)).click();
        waitPage();
        JavascriptExecutor js = (JavascriptExecutor)driver;
       // js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        waitPage();
        WebElement element = driver.findElement(By.xpath(scrollBar));
        js.executeScript("arguments[0].scrollIntoView({block: 'end'});", element);
        String editCodeLines = driver.findElement(By.cssSelector(tCodeLines)).getText();
        String[] codeLinesObj = new String[2];
        codeLinesObj[0] = linesTotal;
        codeLinesObj[1] = editCodeLines;
        return(codeLinesObj);


    }
    @Test
    public void code(){
        String[] codeLinesObj = codeLines();
//        System.out.println("linesTotal: " + codeLinesObj[0] );
//        System.out.println("editCodeLines: " + codeLinesObj[1] );
        Assert.assertEquals(codeLinesObj[0],codeLinesObj[1]);
    }

    @AfterTest
    public void testTeardown()
    {
       driver.quit();
    }
}