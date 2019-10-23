package localbrowsers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;

@Test(groups = {"mac", "windows"})
public class Chrome {
    private WebDriver driver;

    //SET THE SPECIFIC VALUES!!!! HERE (they are static)
    /////////////////////////////SET YOUR EMAIL, PASSWORD and USERNAME
    public String sUserName = "marcelo_0614@hotmail.com";
    public String sPassword = "Pepe.0614asd";
    public String UserInSession = "marcelo0614";
    /////////////////////////////SET THE OWNER OF THE REPO, THE REPO NAME and the FILE that will be used in the test
    public String owner="miguelcoca";
    public String repositorie="SelSimpleDemo";
    public String file="Chrome.java";


    //Initial Page
    public String intialPage="https://github.com";
    public String signIn="//a[@href='/login']";

    //Login page
    public String loginURL = "https://github.com/login";
    public String inputUserName = "login_field";
    public String inputPassword = "password";

    public String buttonCommit = "//input[@name='commit']";
    public String ghUserName = "(//span[contains(text(),'" + UserInSession + "')])[8]";

    //UserPage
    public String findRepositoriesSearchField="//input[@class='form-control input-block mb-3 js-filterable-field js-your-repositories-search']";
    public String searchThisRepositorie=owner+"/"+repositorie;
    public String repositorieFound="//ul[@class='list-style-none filterable-active']";

    //RepoPage
    public String folder="//a[@title='This path skips through empty directories']";
    public String fileToWork="//tbody//td[@class='content']//*[text()[contains(.,'"+file+"')]]";

    //InsideTheFile
    public String description="//span[@class='file-mode']/parent::div";
    public String editButton="//button[@aria-label='Edit the file in your fork of this project']";
    public String scrollbar="//div[@class='CodeMirror-vscrollbar']";
    public String firstRow="//pre[@class=' CodeMirror-line ']";

    @BeforeTest
    public void chromeSetup() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
    }

    @Test
    public void verifyCode() {
        driver.manage().window().maximize();
        driver.navigate().to(intialPage);
        driver.findElement(By.xpath(signIn)).click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.id(inputUserName)).sendKeys(sUserName);
        driver.findElement(By.id(inputPassword)).sendKeys(sPassword);
        driver.findElement(By.xpath(buttonCommit)).click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.xpath(findRepositoriesSearchField)).sendKeys(searchThisRepositorie);
        driver.findElement(By.xpath(repositorieFound)).click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.xpath(folder)).click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.xpath(fileToWork)).click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String totalRows =driver.findElement(By.xpath(description)).getText(); //"Executable File  99 lines (78 sloc)  3.07 KB"
        totalRows=totalRows.replace("Executable File",""); //  99 lines (78 sloc)  3.07 KB
        totalRows=totalRows.trim();//"99 lines (78 sloc)  3.07 KB"
        int sizeTotalRow=  totalRows.length();
        int limit=0;
        for (int n=0; n<sizeTotalRow; n++)   //We will found the position of l of lines in order to catch the value
        {
            char c = totalRows.charAt (n);
            if(c=='l')
            {
                limit=n-1;
                n=sizeTotalRow+1;
            }
        }
        totalRows=totalRows.substring(0, limit); //"99 "
        totalRows=totalRows.trim(); //99

        driver.findElement(By.xpath(editButton)).click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement aux= driver.findElement(By.xpath(firstRow));
        aux.click();
        aux.sendKeys(Keys.PAGE_DOWN);
        aux= driver.findElement(By.xpath(firstRow));
        aux.click();
        aux.sendKeys(Keys.PAGE_DOWN);

        String lastValue="//*[contains(text(),'"+totalRows+"')]";
        String lastRowValue=driver.findElement(By.xpath(lastValue)).getText();
        if(lastRowValue.equals(totalRows))
            System.out.println("Passed");
        else
            System.out.println("Failed");
    }

    @AfterTest
    public void testTeardown()
    {
        driver.quit();
    }
}
