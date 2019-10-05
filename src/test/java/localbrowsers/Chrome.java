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

import java.util.List;

@Test(groups = {"mac", "windows"})
public class Chrome {
    private WebDriver driver;

    public String gTitleLocator = ".g-title";
    public String lButton = "//a[@href='https://seleniumaboveandbeyond.com' and contains(@class,'button')]";

    //Login page
    public String loginURL = "https://github.com/login";
    public String inputUserName = "login_field";
    public String sUserName = "marcelo_0614@hotmail.com";
    public String inputPassword = "password";
    public String sPassword = "xxxxx";
    public String buttonCommit = "//input[@name='commit']";
    public String UserInSession = "marcelo0614";
    public String ghUserName = "(//span[contains(text(),'" + UserInSession + "')])[8]";

    //My Repositories Page
    public String myRepositoriesURL = "https://github.com/" + UserInSession + ";";
    public String setStatus = "//div[@class='d-inline-block text-gray-dark']";
    public String editProfile = "//div[@class='hide-sm hide-md']";
    public String repositories = "//nav[@class='UnderlineNav-body']/child::a[2]";
    public String user = "//summary[@aria-label='View profile and more']";
    public String yourRepositories = "//a[@data-ga-click='Header, go to repositories, text:your repositories']";
    public String arrayElements[] = {setStatus, editProfile, repositories, user, yourRepositories};

    //Project
    public String projectURL="https://github.com/miguelcoca/SelSimpleDemo/pull/1/files";
    public String pullRequest="//a[@class='js-selected-navigation-item selected reponav-item']";
    public String symbol="//td[@class='blob-num blob-num-expandable']";
    public String plusSymbol="//span[@data-code-marker='+']";
    public String arrayElements2[] = {pullRequest, symbol, plusSymbol};



    @BeforeTest
    public void chromeSetup() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
    }

    @Test
    public void login() {
        loginAction();
        Assert.assertNotNull(driver.findElement(By.xpath(ghUserName)));
    }

    @test
    public void identifyElementsMyRepos() {
        result = false
        int size=arrayElements.length;
        driver.navigate().to(myRepositoriesURL);
        for (int i; i <= size; i++) {
            result = thereIs(arrayElements[i])
            result = false;
        }
    }
    @test
    public void identifyElementsHisRepo() {
        result = false;
        int size=arrayElements2.length;
        driver.navigate().to(myRepositoriesURL);
        for (int i; i <= size; i++) {
            result = thereIs(arrayElements2[i])
            System.out.println(result);
            result = false;
        }
    }
    public boolean thereIs(String a) {
        boolean isPresent = false;
        isPresent = driver.findElements(By.xpath(a)).size() > 0;
        if (isPresent) {
            System.out.println("Found");
        } else {
            System.out.println("No found");
        }
        return isPresent;
    }

    private void loginAction() {
        driver.navigate().to(loginURL);
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
